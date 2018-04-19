package rush.comandos;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import rush.utils.ConfigManager;
import rush.utils.DataManager;

public class ComandoSetwarp implements Listener, CommandExecutor {
	
	@Override
	 public boolean onCommand(final CommandSender s, Command cmd, String lbl, String[] args) {
		 if (cmd.getName().equalsIgnoreCase("setwarp")) {
			 if (s.hasPermission("system.setwarp")) {
				 if (s instanceof Player) {
					 
					 Player p = (Player) s;
					 
				     if (args.length == 0) {
				          s.sendMessage(ConfigManager.getConfig("mensagens").getString("SetWarp-Comando-Incorreto").replaceAll("&", "�"));
				          return false;
				     }
				     
				     if (args.length > 1) {
				          s.sendMessage(ConfigManager.getConfig("mensagens").getString("SetWarp-Comando-Incorreto").replaceAll("&", "�"));
				          return false;
				     }
				     
				     else {
				    	String warp = args[0];
				        File file = DataManager.getFile(warp, "Warps");
				        FileConfiguration config = DataManager.getConfiguration(file);
				        if (!file.exists()) {
							DataManager.createFile(file);
							s.sendMessage(ConfigManager.getConfig("mensagens").getString("Warp-Definida").replace("&", "�").replace("%warp%", warp));
							Location location = p.getLocation();
							String locationSerialized = location.getWorld().getName() + "," + location.getX() + "," + location.getY() + "," + location.getZ() + "," + location.getYaw() + "," + location.getPitch();
							config.set("Localizacao" , locationSerialized);
							config.set("Permissao" , "system.warp." + warp.toLowerCase());
							config.set("MensagemSemPermissao", "&cVoc� n�o tem permiss�o para se teleportar para a warp " + warp + "!");
							config.set("Delay", 5);
							config.set("DelayParaVips", false);
							config.set("MensagemInicio" , "&aVoc� sera teleportado para a warp " + warp + " em 5 segundos!");
							config.set("MensagemFinal" , "&aTeleportado com sucesso para a warp " + warp + "!");
							config.set("EnviarTitle" , true);
							config.set("Title" , "&aVOC� FOI TELEPORTADO");
							config.set("SubTitle" , "&ePARA A WARP " + warp.toUpperCase());
							try {
								config.save(file);
							} catch (IOException e) {
								Bukkit.getConsoleSender().sendMessage(ConfigManager.getConfig("mensagens").getString("Falha-Ao-Salvar").replace("&", "�").replace("%arquivo%", file.getName()));
							}
						} else {
				        	s.sendMessage(ConfigManager.getConfig("mensagens").getString("Warp-Ja-Existe").replace("&", "�").replace("%warp%", warp));
				        }
				     }
					 return false;
				 }
				 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Console-Nao-Pode").replace("&", "�"));
				 return false;
			 }
			 s.sendMessage(ConfigManager.getConfig("mensagens").getString("Sem-Permissao").replace("&", "�"));
			 return false;
		 }
		return false;
	}
}
