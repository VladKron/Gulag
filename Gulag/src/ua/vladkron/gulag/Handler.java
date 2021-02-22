package ua.vladkron.gulag;

import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Handler implements Listener{	
	private static Main pluginInstance = Main.getInstance();	 //Получаем адрес класса Main
	//PlayerJoinEvent - вызывается, когда игрок заходит на сервер.	
	@EventHandler	// @аннотация
	public void Command(PlayerDeathEvent e) {		// Это будет выполняться когда игрок умер. На вход метода получаем класс EntityDeathEvent
		Player p = e.getEntity();			// Получили умершего игрока
		FileConfiguration config_yml = pluginInstance.getConfig();
		List<String> prisoners = config_yml.getStringList("prisoners"); // Взять всех заключенных из .yml в виде списка   	
		
		if(prisoners.contains(p.getName())) {			// Проверяем является умерший игрок одним из списка
			p.setBedSpawnLocation(Settings.gulagLocation, true);	
		}
	}		
}
