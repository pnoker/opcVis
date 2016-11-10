package com.opcVis.util.opc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafish.clients.opc.JOpc;
import javafish.clients.opc.SynchReadItemExample;
import javafish.clients.opc.browser.JOpcBrowser;
import javafish.clients.opc.component.OpcGroup;
import javafish.clients.opc.component.OpcItem;
import javafish.clients.opc.exception.ComponentNotFoundException;
import javafish.clients.opc.exception.ConnectivityException;
import javafish.clients.opc.exception.SynchReadException;
import javafish.clients.opc.exception.SynchWriteException;
import javafish.clients.opc.exception.UnableAddGroupException;
import javafish.clients.opc.exception.UnableAddItemException;
import javafish.clients.opc.variant.Variant;

public class OPC {

	private JOpc jopc = null;
	private OpcGroup group = null;

	public OPC() {
		String fileName, host = "", serverName = "";
		List<String> array = new ArrayList<String>();

		fileName = "C:\\wangjian\\tag.txt";

		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = reader.readLine()) != null) {
				if (line == 1) {
					host = tempString;
					System.out.println("host:" + host);
				} else if (line == 2) {
					serverName = tempString;
					System.out.println("serverName:" + serverName);
				} else {
					array.add(tempString);
					System.out.println(tempString);
				}
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

		JOpcBrowser.coInitialize();
		String[] servers;
		// try {
		// servers = JOpcBrowser.getOpcServers(host);
		// if (servers != null) {
		// System.out.println(Arrays.asList(servers));
		// } else {
		// System.out.println("Array Servers is null.");
		// }
		// } catch (HostException e3) {
		// e3.printStackTrace();
		// } catch (NotFoundServersException e3) {
		// e3.printStackTrace();
		// }

		SynchReadItemExample test = new SynchReadItemExample();

		JOpc.coInitialize();

		jopc = new JOpc(host, serverName, "JOPC1");

		group = new OpcGroup("group1", true, 500, 0.0f);

		OpcItem item = null;

		for (int i = 0; i < array.size(); i++) {
			item = new OpcItem(array.get(i), true, "");
			group.addItem(item);
		}

		jopc.addGroup(group);

		try {
			jopc.connect();
			System.out.println("JOPC client is connected...");
		} catch (ConnectivityException e2) {
			e2.printStackTrace();
		}

		try {
			jopc.registerGroups();
			System.out.println("OPCGroup are registered...");
		} catch (UnableAddGroupException e2) {
			e2.printStackTrace();
		} catch (UnableAddItemException e2) {
			e2.printStackTrace();
		}
	}

	public String read(String tag) {
		// System.out.println("OPC read:" + tag);
		OpcGroup responseGroup;
		try {
			responseGroup = jopc.synchReadGroup(group);
			List<OpcItem> list = responseGroup.getItems();
			OpcItem item = null;
			for (int i = 0; i < list.size(); i++) {
				item = list.get(i);
				if (item.getItemName().equals(tag)) {
					return item.getValue().toString();
				}
			}
			return "error";
		} catch (ComponentNotFoundException e) {
			e.printStackTrace();
			return "";
		} catch (SynchReadException e) {
			e.printStackTrace();
			return "";
		}
	}

	public String readString(String tag) {
		String ret = read(tag);
		// System.out.println("OPC读取数值为" + ret + "。");
		if (ret.equals("true")) {
			return "1";
		} else if (ret.equals("false")) {
			return "0";
		} else {
			return ret;
		}
	}

	// public int readInt(String tag) {
	// return Integer.parseInt(read(tag));
	// }
	//
	// public float readFloat(String tag) {
	// return Float.parseFloat(read(tag));
	// }

	public void writeFloat(String tag, float value) {
		// System.out.println("OPC write:" + tag);
		List<OpcItem> itemsArray = group.getItems();
		OpcItem item = null;
		String currentTag = null;
		for (int i = 0; i < itemsArray.size(); i++) {
			item = itemsArray.get(i);
			currentTag = item.getItemName();
			if (tag.equals(currentTag)) {
				item.setValue(new Variant(value));
				try {
					jopc.synchWriteItem(group, item);
				} catch (ComponentNotFoundException e) {
					e.printStackTrace();
				} catch (SynchWriteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// public void writeInt(String tag, int value) {
	// System.out.println("OPC write:" + tag);
	// List<OpcItem> itemsArray = group.getItems();
	// OpcItem item = null;
	// String currentTag = null;
	// for(int i = 0; i < itemsArray.size(); i++) {
	// item = itemsArray.get(i);
	// currentTag = item.getItemName();
	// if(tag.equals(currentTag)) {
	// item.setValue(new Variant(value));
	// try {
	// jopc.synchWriteItem(group, item);
	// } catch (ComponentNotFoundException e) {
	// e.printStackTrace();
	// } catch (SynchWriteException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	// }

	public void writeString(String tag, String value) {
		// System.out.println("OPC writeString:" + tag);
		List<OpcItem> itemsArray = group.getItems();
		OpcItem item = null;
		String currentTag = null;
		for (int i = 0; i < itemsArray.size(); i++) {
			item = itemsArray.get(i);
			currentTag = item.getItemName();
			if (tag.equals(currentTag)) {
				item.setValue(new Variant(value));
				try {
					jopc.synchWriteItem(group, item);
				} catch (ComponentNotFoundException e) {
					e.printStackTrace();
				} catch (SynchWriteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void writeStringCommit(String tag, String value) {
		// System.out.println("OPC writeStringCommit:" + tag);
		while (true) {
			writeString(tag, value);
			if (value.equals(readString(tag))) {
				// System.out.println("写入成功");
				break;
			} else {
				// System.out.println("再次写入");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// List<OpcItem> itemsArray = group.getItems();
		// OpcItem item = null;
		// String currentTag = null;
		// for(int i = 0; i < itemsArray.size(); i++) {
		// item = itemsArray.get(i);
		// currentTag = item.getItemName();
		// if(tag.equals(currentTag)) {
		// item.setValue(new Variant(value));
		// try {
		// jopc.synchWriteItem(group, item);
		// while(true) {
		// if(value.equals(readString(tag))) {
		// System.out.println("写入成功");
		// break;
		// }
		// else {
		// System.out.println("再次写入");
		// Thread.sleep(500);
		// }
		// }
		// } catch (ComponentNotFoundException e) {
		// e.printStackTrace();
		// } catch (SynchWriteException e) {
		// e.printStackTrace();
		// }catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// }
	}
}
