package ua.vladkron.gulag;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ua.vladkron.gulag.commands.GulagCommand;


public class Main extends JavaPlugin{
	
	 private static Main instance;	// Тут будет хранитться адрес нашего класса Main
	
	 @Override
	 public void onLoad() {	// Как только загружаеться плагин майнкрафтом 
		 instance = this;   // Мы сохраняем адрес где хранится наш плагин в переменную instance
	 }
	 
    public static Main getInstance() {	// Создали метод который возвращает  адрес нашего класса
   	 return instance;
	 }
    
	// Called when this plugin is enabled
	@Override 
	public void onEnable() {		// переопределяем метод onEnable из класса JavaPlugin
		getLogger().severe("Advertising plugin started");		// коммент из плагина в консоль майнкрафт
		
		FileConfiguration config_yml = this.getConfig();
    	String world = config_yml.getString("gulag.world");		// Взять одно значение из .yml
    	String x = config_yml.getString("gulag.x");
    	String y = config_yml.getString("gulag.y");
    	String z = config_yml.getString("gulag.z");
    	Settings.gulagLocation = new Location(Bukkit.getWorld(world), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));		// Создаем кординату ГУЛАГА
    	
	 	//регистрируем обработчик собитый манкрафта. Это УХО в которое майнкрафт будет кричать при каждом событии в игре 
	 	Bukkit.getPluginManager().registerEvents(new Handler(), this);
	 	
	 	File config = new File(getDataFolder() + File.separator + Settings.fileName);
	 	if(!config.exists()) {		// Если конфига нет 
	 		getConfig().options().copyDefaults(true); 		// то копируем его из проекта
	 		saveDefaultConfig();
	 	}

	 	getCommand("gulag").setExecutor(new GulagCommand());
	 	
	}
	// Called when this plugin is disabled
	@Override 
	public void onDisable() {	
	}
}
