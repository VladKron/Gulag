package ua.vladkron.gulag;

import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Handler implements Listener{	
	private static Main pluginInstance = Main.getInstance();	 //�������� ����� ������ Main
	//PlayerJoinEvent - ����������, ����� ����� ������� �� ������.	
	@EventHandler	// @���������
	public void Command(PlayerDeathEvent e) {		// ��� ����� ����������� ����� ����� ����. �� ���� ������ �������� ����� EntityDeathEvent
		Player p = e.getEntity();			// �������� �������� ������
		FileConfiguration config_yml = pluginInstance.getConfig();
		List<String> prisoners = config_yml.getStringList("prisoners"); // ����� ���� ����������� �� .yml � ���� ������   	
		
		if(prisoners.contains(p.getName())) {			// ��������� �������� ������� ����� ����� �� ������
			p.setBedSpawnLocation(Settings.gulagLocation, true);	
		}
	}		
}
