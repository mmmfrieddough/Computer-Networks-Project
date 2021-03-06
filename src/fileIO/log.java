package fileIO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.BitSet;
import java.util.Map;

import behavior.RemotePeerInfo;

public class log {
	private int peer_ID;
	private File logFile;
	private FileOutputStream logOutputStream;
	
	public log(int ID) throws IOException {
		this.peer_ID = ID;
		logFile = new File("log_peer_" + peer_ID + ".log");
		logFile.createNewFile();
		logOutputStream = new FileOutputStream(logFile, false); 
	}
	
	public void logTCPTo(int peer_ID_2) throws IOException {
		logOutputStream.write((LocalDateTime.now() + ": Peer " + peer_ID + " makes a connection to Peer " + peer_ID_2 + "." + System.getProperty("line.separator")).getBytes());
	}
	
	public void logTCPFrom(int peer_ID_2) throws IOException {
		logOutputStream.write((LocalDateTime.now() + ": Peer " + peer_ID + " is connected from Peer " + peer_ID_2 + "." + System.getProperty("line.separator")).getBytes());
	}
	
	public void logChangePreferedNeighbors(Map<RemotePeerInfo, BitSet> neighbors) throws IOException {
		String logText = LocalDateTime.now() + ": Peer " + peer_ID + " has the preferred neighbors ";
		String comma = "";
		for (RemotePeerInfo neighbor : neighbors.keySet()) {
			logText += comma;
			logText += neighbor.getPeerID();
			comma = ",";
		}
		logText += ".";
		logText += System.getProperty("line.separator");
		logOutputStream.write(logText.getBytes());
	}
	
	public void logOptimisticallyUnchoked(int peer_ID_2) throws IOException {
		logOutputStream.write((LocalDateTime.now() + ": Peer " + peer_ID + " has the optimistically unchoked neighbor " + peer_ID_2 + "." + System.getProperty("line.separator")).getBytes());
	}
	
	public void logUnchoked(int peer_ID_2) throws IOException {
		logOutputStream.write((LocalDateTime.now() + ": Peer " + peer_ID + " is unchoked by " + peer_ID_2 + "." + System.getProperty("line.separator")).getBytes());
	}
	
	public void logChoked(int peer_ID_2) throws IOException {
		logOutputStream.write((LocalDateTime.now() + ": Peer " + peer_ID + " is choked by " + peer_ID_2 + "." + System.getProperty("line.separator")).getBytes());
	}
	
	public void logHave(int peer_ID_2, int pieceIndex) throws IOException {
		logOutputStream.write((LocalDateTime.now() + ": Peer " + peer_ID + " received the 'have' message from " + peer_ID_2 + " for the piece " + pieceIndex + "." + System.getProperty("line.separator")).getBytes());
	}
	
	public void logInterested(int peer_ID_2) throws IOException {
		logOutputStream.write((LocalDateTime.now() + ": Peer " + peer_ID + " received the 'interested' message from " + peer_ID_2 + "." + System.getProperty("line.separator")).getBytes());
	}
	
	public void logNotInterested(int peer_ID_2) throws IOException {
		logOutputStream.write((LocalDateTime.now() + ": Peer " + peer_ID + " received the 'not interested' message from " + peer_ID_2 + "." + System.getProperty("line.separator")).getBytes());
	}
	
	public void logDownloadedPiece(int peer_ID_2, int pieceIndex, int numPieces) throws IOException {
		logOutputStream.write((LocalDateTime.now() + ": Peer " + peer_ID + " has downloaded the piece " + pieceIndex + " from " + peer_ID_2 + ".\nNow the number of pieces it has is " + numPieces + "." + System.getProperty("line.separator")).getBytes());
	}
	
	public void logDownloadCompletion() throws IOException {
		logOutputStream.write((LocalDateTime.now() + ": Peer " + peer_ID + " has downloaded the complete file." + System.getProperty("line.separator")).getBytes());
	}
}
