package ua.vladkron.gulag.commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import ua.vladkron.gulag.Main;
import ua.vladkron.gulag.Settings;

public class GulagCommand implements CommandExecutor {
	private static Main pluginInstance = Main.getInstance();	 //�������� ����� ������ Main

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("gulag")) {
			if(args.length != 1) {						// ����� ������� ��� ����������
				sender.sendMessage(ChatColor.RED +  "������� �������� � �������: /gulag <Player>");
				sender.sendMessage(ChatColor.RED +  "��� �������� ������������� ����������� ������ � �����");		
				return false;
			}
			else {
				Player p = Bukkit.getPlayerExact(args[0]);
			    if(p == null)
			    {
			        sender.sendMessage(ChatColor.RED + "The player could not be found");
			        return false;
			    }
			    else if(!sender.hasPermission("gulag.set")) {
			    	sender.sendMessage(ChatColor.RED + "You are not permitted to use this command!");
			        return false;
			    }
			    else {
			    	//sender.sendMessage("The player is ok");
			    	
			    	FileConfiguration config_yml = pluginInstance.getConfig();
					String world = config_yml.getString("gulag.world");		// ����� ���� �������� �� .yml
					String x = config_yml.getString("gulag.x");
					String y = config_yml.getString("gulag.y");
					String z = config_yml.getString("gulag.z");
					
			    	Location l = new Location(Bukkit.getWorld(world), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));		// ������� ��������� ������
			    	p.setBedSpawnLocation(l, true);			// ������ ����� ������ ������ � ������
			    	p.setHealth(0);							// ������� ������
			    	
			    	List<String> prisoners = config_yml.getStringList("prisoners"); // ����� ���� ����������� �� .yml � ���� ������   	
			    	prisoners.add(p.getName());										// �������� ��� ������ ������������ � ������
			        config_yml.set("prisoners", prisoners);							// �������� ����� ������ ����������� � .yml
			        try {
			        	config_yml.save(Settings.dir + File.separator + Settings.fileName);	// ��������� .yml
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			    }		
			}
		}
	return false;
	}
}

