package com.LeoGamerG0D.motd;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerOptions;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
	    ProtocolLibrary.getProtocolManager().addPacketListener(
	      new PacketAdapter(this, ListenerPriority.NORMAL,
	      Arrays.asList(PacketType.Status.Server.OUT_SERVER_INFO), ListenerOptions.ASYNC) {
	 
	        @Override
	        public void onPacketSending(PacketEvent event) {
	            handlePing(event.getPacket().getServerPings().read(0));
	        }
	    });
	    getServer().getPluginManager().registerEvents(this, this);
	}
	 
	@SuppressWarnings("deprecation")
	private void handlePing(WrappedServerPing ping) {
		  if (Bukkit.getServer().hasWhitelist()){
			  ping.setPlayers(Arrays.asList(
				        new WrappedGameProfile("id1", "§cEstamos em manutenção!")
				    ));
				    ping.setVersionProtocol(99);
				    ping.setVersionName("Manutenção");
		  } else {
			  ping.setPlayers(Arrays.asList(
				        new WrappedGameProfile("id2", "§ewww.redemania.com")
				    ));
		  }
	}
	@EventHandler
	public void onPing(ServerListPingEvent e){
		if (Bukkit.getServer().hasWhitelist()){
			e.setMotd("§6§lSmart§f§lMC §7(1.8.*)\n§cServidor em manutenção!");
		} else {
			e.setMotd("§6§lSmart§f§lMC §7(1.8.*)\n§fwww.redemania.com");
		}
	}
}
