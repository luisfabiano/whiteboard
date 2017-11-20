package com.mp.serice.core.net.server.telnet.app;

import com.mp.serice.core.net.server.exception.ServerInitException;
import com.mp.serice.core.net.server.telnet.SimpleTelnetServer;

public class TelnetServerApp {

	public static void main(String[] args) {
		SimpleTelnetServer server = null;
		try {
			server = new SimpleTelnetServer(8001, "192.168.10.34");
			server.open();
		} catch (ServerInitException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}