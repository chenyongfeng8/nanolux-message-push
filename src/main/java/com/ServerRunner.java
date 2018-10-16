package com;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
 
import com.corundumstudio.socketio.SocketIOServer;
 
@Component
public class ServerRunner implements CommandLineRunner {
	private final SocketIOServer server;
 
    @Autowired
    public ServerRunner(SocketIOServer server) {
        this.server = server;
    }
 
    public void run(String... args) throws Exception {
        server.start();
    }
}
