package com.d4rkr34lm.wgbuild.trail;

import com.d4rkr34lm.wgbuild.WGBuild;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TrailCommand implements CommandExecutor {

    private boolean trailShown = false;

    private Player player;

    private final String PLAYER_ONLY_USE = "§cThis command is only meant to be used by a Player";
    private final String COMMAND_USE = "§cPlease use §6/trial <new/hide/explosion/travel/normal>";

    private final String ALREADY_RECORDING = "§aThe recording has already started. U silly little bastard";

    private final String RECORDING_STARTED = "§aStarted recording. Waiting for TNT...";

    public TrailCommand(){
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(PLAYER_ONLY_USE);
            return false;
        }

        player = (Player) sender;

        if(args.length != 1){
            player.sendMessage(COMMAND_USE);
            return false;
        }

        String operator = args[0];

        switch (operator){
            case "new":
                newTrail();
                break;
            case "hide":
                hideTrail();
                break;
            case "explosion":
                showExplosionOnly();
                break;
            case "travel":
                showTravelOnly();
                break;
            case "normal":
                showNormalTrail();
        }

        return false;

    }

    private void showNormalTrail() {
    }

    private void showTravelOnly() {
    }

    private void showExplosionOnly() {
    }

    private void hideTrail() {
    }

    private void newTrail() {

        if(WGBuild.isWaitingToStartRecording()){
            player.sendMessage(ALREADY_RECORDING);
            return;
        }

        player.sendMessage(RECORDING_STARTED);
        WGBuild.setWaitingToStartRecording(true);

    }

}
