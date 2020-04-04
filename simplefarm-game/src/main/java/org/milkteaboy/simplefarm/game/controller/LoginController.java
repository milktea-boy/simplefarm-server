package org.milkteaboy.simplefarm.game.controller;

import io.netty.channel.ChannelHandlerContext;
import org.milkteaboy.simplefarm.entity.User;
import org.milkteaboy.simplefarm.game.constant.StaticData;
import org.milkteaboy.simplefarm.netty.socket.SocketServer;
import org.milkteaboy.simplefarm.service.AccountService;
import org.milkteaboy.simplefarm.service.exception.AccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * login模块Controller
 */
@Controller("login")
public class LoginController {

    @Autowired
    private SocketServer socketServer;
    @Autowired
    private AccountService accountService;

    /**
     * 登录
     * @param ctx 通道
     * @param username 用户名
     * @param password 密码
     */
    public void login(ChannelHandlerContext ctx, String username, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            User user = accountService.login(username, password);
            if (StaticData.userInfo.containsKey(ctx)) {
                StaticData.userInfo.replace(ctx, user);
            } else {
                StaticData.userInfo.put(ctx, user);
            }
            map.put("success", true);
            map.put("message", "登录成功");
        } catch (AccountException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "登录失败");
            e.printStackTrace();
        }
        socketServer.sendMessage(ctx, "login/login", map);
    }

    /**
     * 注册
     * @param ctx 通道
     * @param username 用户名
     * @param password 密码
     * @param nickname 昵称
     */
    public void register(ChannelHandlerContext ctx, String username, String password, String nickname) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            accountService.register(username, password, nickname);
            map.put("success", true);
            map.put("message", "注册成功");
        } catch (AccountException e) {
            map.clear();
            map.put("success", false);
            map.put("message", e.getMessage());
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "注册失败");
            e.printStackTrace();
        }
        socketServer.sendMessage(ctx, "login/register", map);
    }

    /**
     * 退出登录
     * @param ctx 通道
     */
    public void logout(ChannelHandlerContext ctx) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (StaticData.userInfo.containsKey(ctx)) {
                StaticData.userInfo.remove(ctx);
                map.put("success", true);
                map.put("message", "退出成功");
            } else {
                map.put("success", false);
                map.put("message", "用户未登录");
            }
        } catch (Exception e) {
            map.clear();
            map.put("success", false);
            map.put("message", "退出失败");
            e.printStackTrace();
        }
        socketServer.sendMessage(ctx, "login/logout", map);
    }

}