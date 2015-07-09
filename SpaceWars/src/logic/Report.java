package logic;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface Report extends Serializable{
	public void addReport(Report report) ;
}
