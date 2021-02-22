package ua.vladkron.gulag;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ua.vladkron.gulag.commands.GulagCommand;


public class Main extends JavaPlugin{
	
	 private static Main instance;	// ��� ����� ���������� ����� ������ ������ Main
	
	 @Override
	 public void onLoad() {	// ��� ������ ������������ ������ ����������� 
		 instance = this;   // �� ��������� ����� ��� �������� ��� ������ � ���������� instance
	 }
	 
    public static Main getInstance() {	// ������� ����� ������� ����������  ����� ������ ������
   	 return instance;
	 }
    
	// Called when this plugin is enabled
	@Override 
	public void onEnable() {		// �������������� ����� onEnable �� ������ JavaPlugin
		getLogger().severe("Advertising plugin started");		// ������� �� ������� � ������� ���������
		
		FileConfiguration config_yml = this.getConfig();
    	String world = config_yml.getString("gulag.world");		// ����� ���� �������� �� .yml
    	String x = config_yml.getString("gulag.x");
    	String y = config_yml.getString("gulag.y");
    	String z = config_yml.getString("gulag.z");
    	Settings.gulagLocation = new Location(Bukkit.getWorld(world), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));		// ������� ��������� ������
    	
	 	//������������ ���������� ������� ���������. ��� ��� � ������� ��������� ����� ������� ��� ������ ������� � ���� 
	 	Bukkit.getPluginManager().registerEvents(new Handler(), this);
	 	
	 	File config = new File(getDataFolder() + File.separator + Settings.fileName);
	 	if(!config.exists()) {		// ���� ������� ��� 
	 		getConfig().options().copyDefaults(true); 		// �� �������� ��� �� �������
	 		saveDefaultConfig();
	 	}

	 	getCommand("gulag").setExecutor(new GulagCommand());
	 	
	}
	// Called when this plugin is disabled
	@Override 
	public void onDisable() {	
	}
}
