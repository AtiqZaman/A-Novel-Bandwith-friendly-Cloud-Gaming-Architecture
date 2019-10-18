// file: RunShellCommandFromJava.java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameLuncherCanada implements Runnable  {

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Process pr = new ProcessBuilder("gnome-terminal", "-e","/media/baloch/5450EFE350EFC9B6/GA/gaminganywhere-master/bin/runClientscript.sh").start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*
	 * public static void main(String[] args) { GameLuncer gl = new GameLuncer();
	 * gl.Run(); }
	 */
	 
}