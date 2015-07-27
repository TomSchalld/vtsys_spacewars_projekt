package logic;

import java.io.Serializable;
import java.rmi.RemoteException;

import org.json.JSONObject;

public interface Report extends Serializable{
	public void addReport(Report report);

}
