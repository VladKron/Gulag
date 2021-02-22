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
	private static Main pluginInstance = Main.getInstance();	 //Получаем адрес класса Main

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(command.getName().equalsIgnoreCase("gulag")) {
			if(args.length != 1) {						// ввели команду без параметров
				sender.sendMessage(ChatColor.RED +  "Введите комманду в формате: /gulag <Player>");
				sender.sendMessage(ChatColor.RED +  "Эта комманда телепортирует нарушителей режима в ГУЛАГ");		
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
					String world = config_yml.getString("gulag.world");		// Взять одно значение из .yml
					String x = config_yml.getString("gulag.x");
					String y = config_yml.getString("gulag.y");
					String z = config_yml.getString("gulag.z");
					
			    	Location l = new Location(Bukkit.getWorld(world), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));		// Создаем кординату ГУЛАГА
			    	p.setBedSpawnLocation(l, true);			// Ставим точку спавна игрока в ГУЛАГЕ
			    	p.setHealth(0);							// Убиваем игрока
			    	
			    	List<String> prisoners = config_yml.getStringList("prisoners"); // Взять всех заключенных из .yml в виде списка   	
			    	prisoners.add(p.getName());										// Добавили еще одного заключенного в список
			        config_yml.set("prisoners", prisoners);							// Добавили новый список заключенных в .yml
			        try {
			        	config_yml.save(Settings.dir + File.separator + Settings.fileName);	// Сохранили .yml
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			    }		
			}
		}
	return false;
	}
}

