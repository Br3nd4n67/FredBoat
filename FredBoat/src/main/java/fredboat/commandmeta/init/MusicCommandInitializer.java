/*
 * MIT License
 *
 * Copyright (c) 2017 Frederik Ar. Mikkelsen
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fredboat.commandmeta.init;

import fredboat.command.admin.AnnounceCommand;
import fredboat.command.admin.BotRestartCommand;
import fredboat.command.admin.EvalCommand;
import fredboat.command.admin.ExitCommand;
import fredboat.command.admin.GetNodeCommand;
import fredboat.command.admin.LeaveServerCommand;
import fredboat.command.admin.NodeAdminCommand;
import fredboat.command.admin.PlayerDebugCommand;
import fredboat.command.admin.ReviveCommand;
import fredboat.command.admin.SentryDsnCommand;
import fredboat.command.admin.SetAvatarCommand;
import fredboat.command.admin.UnblacklistCommand;
import fredboat.command.maintenance.AudioDebugCommand;
import fredboat.command.maintenance.DebugCommand;
import fredboat.command.maintenance.GetIdCommand;
import fredboat.command.maintenance.GitInfoCommand;
import fredboat.command.maintenance.NodesCommand;
import fredboat.command.maintenance.PingCommand;
import fredboat.command.maintenance.ShardsCommand;
import fredboat.command.maintenance.StatsCommand;
import fredboat.command.moderation.ConfigCommand;
import fredboat.command.moderation.DisableCommandsCommand;
import fredboat.command.moderation.EnableCommandsCommand;
import fredboat.command.moderation.LanguageCommand;
import fredboat.command.moderation.PermissionsCommand;
import fredboat.command.music.control.DestroyCommand;
import fredboat.command.music.control.JoinCommand;
import fredboat.command.music.control.LeaveCommand;
import fredboat.command.music.control.PauseCommand;
import fredboat.command.music.control.PlayCommand;
import fredboat.command.music.control.PlaySplitCommand;
import fredboat.command.music.control.RepeatCommand;
import fredboat.command.music.control.ReshuffleCommand;
import fredboat.command.music.control.SelectCommand;
import fredboat.command.music.control.ShuffleCommand;
import fredboat.command.music.control.SkipCommand;
import fredboat.command.music.control.StopCommand;
import fredboat.command.music.control.UnpauseCommand;
import fredboat.command.music.control.VolumeCommand;
import fredboat.command.music.control.VoteSkipCommand;
import fredboat.command.music.info.ExportCommand;
import fredboat.command.music.info.GensokyoRadioCommand;
import fredboat.command.music.info.HistoryCommand;
import fredboat.command.music.info.ListCommand;
import fredboat.command.music.info.NowplayingCommand;
import fredboat.command.music.seeking.ForwardCommand;
import fredboat.command.music.seeking.RestartCommand;
import fredboat.command.music.seeking.RewindCommand;
import fredboat.command.music.seeking.SeekCommand;
import fredboat.command.util.CommandsCommand;
import fredboat.command.util.HelpCommand;
import fredboat.command.util.MusicHelpCommand;
import fredboat.command.util.UserInfoCommand;
import fredboat.commandmeta.CommandRegistry;
import fredboat.perms.PermissionLevel;
import fredboat.util.rest.SearchUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;

public class MusicCommandInitializer {

    private static final Logger log = LoggerFactory.getLogger(MusicCommandInitializer.class);

    public static void initCommands() {
        CommandRegistry.registerCommand(new HelpCommand("help", "info"));
        CommandRegistry.registerCommand(new MusicHelpCommand("music", "musichelp"));
        CommandRegistry.registerCommand(new CommandsCommand("commands", "comms", "cmds"));
        
        /* Control */
        CommandRegistry.registerCommand(new PlayCommand(Arrays.asList(SearchUtil.SearchProvider.YOUTUBE, SearchUtil.SearchProvider.SOUNDCLOUD),
                "play", "p"));
        CommandRegistry.registerCommand(new PlayCommand(Collections.singletonList(SearchUtil.SearchProvider.YOUTUBE),
                "youtube", "yt"));
        CommandRegistry.registerCommand(new PlayCommand(Collections.singletonList(SearchUtil.SearchProvider.SOUNDCLOUD),
                "soundcloud", "sc"));
        CommandRegistry.registerCommand(new SkipCommand("skip", "sk", "s"));
        CommandRegistry.registerCommand(new VoteSkipCommand("voteskip", "vsk", "v"));
        CommandRegistry.registerCommand(new JoinCommand("join", "summon", "jn", "j"));
        CommandRegistry.registerCommand(new LeaveCommand("leave", "lv"));
        CommandRegistry.registerCommand(new SelectCommand("select", buildNumericalSelectAllias("sel")));
        CommandRegistry.registerCommand(new StopCommand("stop", "st"));
        CommandRegistry.registerCommand(new PauseCommand("pause", "pa", "ps"));
        CommandRegistry.registerCommand(new ShuffleCommand("shuffle", "sh", "random"));
        CommandRegistry.registerCommand(new ReshuffleCommand("reshuffle", "resh"));
        CommandRegistry.registerCommand(new RepeatCommand("repeat", "rep"));
        CommandRegistry.registerCommand(new VolumeCommand("volume", "vol"));
        CommandRegistry.registerCommand(new UnpauseCommand("unpause", "unp", "resume"));
        CommandRegistry.registerCommand(new PlaySplitCommand("split"));
        CommandRegistry.registerCommand(new DestroyCommand("destroy"));
        
        /* Info */
        CommandRegistry.registerCommand(new NowplayingCommand("nowplaying", "np"));
        CommandRegistry.registerCommand(new ListCommand("list", "queue", "q", "l"));
        CommandRegistry.registerCommand(new HistoryCommand("history", "hist", "h"));
        CommandRegistry.registerCommand(new ExportCommand("export", "ex"));
        CommandRegistry.registerCommand(new GensokyoRadioCommand("gensokyo", "gr", "gensokyoradio"));
        CommandRegistry.registerCommand(new UserInfoCommand("muserinfo"));

        /* Seeking */
        CommandRegistry.registerCommand(new SeekCommand("seek"));
        CommandRegistry.registerCommand(new ForwardCommand("forward", "fwd"));
        CommandRegistry.registerCommand(new RewindCommand("rewind", "rew"));
        CommandRegistry.registerCommand(new RestartCommand("restart", "replay"));
        
        /* Bot Maintenance Commands */
        CommandRegistry.registerCommand(new GitInfoCommand("mgitinfo", "mgit"));
        CommandRegistry.registerCommand(new UnblacklistCommand("munblacklist", "munlimit"));
        CommandRegistry.registerCommand(new ExitCommand("mexit"));
        CommandRegistry.registerCommand(new LeaveServerCommand("mleaveserver"));
        CommandRegistry.registerCommand(new BotRestartCommand("mbotrestart"));
        CommandRegistry.registerCommand(new StatsCommand("mstats"));
        CommandRegistry.registerCommand(new EvalCommand("meval"));
        CommandRegistry.registerCommand(new GetIdCommand("getid"));
        CommandRegistry.registerCommand(new PlayerDebugCommand("playerdebug"));
        CommandRegistry.registerCommand(new NodesCommand("nodes"));
        CommandRegistry.registerCommand(new ShardsCommand("mshards"));
        CommandRegistry.registerCommand(new ReviveCommand("mrevive"));
        CommandRegistry.registerCommand(new SentryDsnCommand("msentrydsn"));
        CommandRegistry.registerCommand(new AudioDebugCommand("adebug"));
        CommandRegistry.registerCommand(new AnnounceCommand("announce"));
        CommandRegistry.registerCommand(new PingCommand("mping"));
        CommandRegistry.registerCommand(new NodeAdminCommand("node"));
        CommandRegistry.registerCommand(new GetNodeCommand("getnode"));
        CommandRegistry.registerCommand(new DisableCommandsCommand("disable"));
        CommandRegistry.registerCommand(new EnableCommandsCommand("enable"));
        CommandRegistry.registerCommand(new DebugCommand("debug"));
        CommandRegistry.registerCommand(new SetAvatarCommand("setavatar"));

        /* Bot configuration */
        CommandRegistry.registerCommand(new ConfigCommand("config", "cfg"));
        CommandRegistry.registerCommand(new LanguageCommand("language", "lang"));
        
        /* Perms */
        CommandRegistry.registerCommand(new PermissionsCommand(PermissionLevel.ADMIN, "admin"));
        CommandRegistry.registerCommand(new PermissionsCommand(PermissionLevel.DJ, "dj"));
        CommandRegistry.registerCommand(new PermissionsCommand(PermissionLevel.USER, "user"));
    }

    /**
     * Build a string array that consist of the max number of searches.
     *
     * @param extraAliases Aliases to be appended to the rest of the ones being built.
     * @return String array that contains string representation of numbers with addOnAliases.
     */
    private static String[] buildNumericalSelectAllias(String... extraAliases) {
        String[] selectTrackAliases = new String[SearchUtil.MAX_RESULTS + extraAliases.length];
        int i = 0;
        for (; i < extraAliases.length; i++) {
            selectTrackAliases[i] = extraAliases[i];
        }
        for (; i < SearchUtil.MAX_RESULTS + extraAliases.length; i++) {
            selectTrackAliases[i] = String.valueOf(i - extraAliases.length + 1);
        }
        return selectTrackAliases;
    }
}
