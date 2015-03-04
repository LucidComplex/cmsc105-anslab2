package anslab2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Data {
	private String mDataName;
	private List<String> mDataList;
	private TreeMap<String, Integer> mDataTree;
	
	private static final int COLUMN_SIZE = 20;
	
	public Data() {
		mDataList = new LinkedList<String>();
		mDataTree = new TreeMap<String, Integer>(new StringComparator());
	}
	
	public List<String> getDataList() {
		return mDataList;
	}
	
	public String getDataName() {
		return mDataName;
	}
	
	public void setDataName(String name) {
		mDataName = name;
	}
	
	public void setData(String input) {
		// parse input
		String[] split = input.split("[,]+");
		for(String data : split) {
			String trimmed = data.trim();
			mDataList.add(trimmed);
			if(mDataTree.containsKey(trimmed))
				mDataTree.put(trimmed, mDataTree.get(trimmed) + 1);
			else
				mDataTree.put(trimmed, 1);
		}
	}
	
	public String asRawTable() {
		StringBuilder builder = new StringBuilder();
		builder.append(":::Raw \"" + mDataName + "\" Data:::\n");
		
		for(int i = 0; i < COLUMN_SIZE * 2; i++)
			builder.append("-");
		builder.append("\n");
		
		// print data
		for(String data : mDataList)
			builder.append(data + "\n");
		
		return builder.toString();
	}
	
	public String asFDTable() {
		Iterator<String> itr = mDataTree.keySet().iterator();
		StringBuilder builder = new StringBuilder();
		builder.append(mDataName);
		for(int i = mDataName.length(); i < COLUMN_SIZE; i++)
			builder.append(" ");
		builder.append(" Frequency\n");
		
		
		for(int i = 0; i < COLUMN_SIZE * 2; i++)
			builder.append("-");
		builder.append("\n");
		
		while(itr.hasNext()) {
			String data = itr.next();
			builder.append(data);
			for(int j = data.length(); j < COLUMN_SIZE; j++)
				builder.append(" ");
			builder.append("|");
			for(int j = 0; j < COLUMN_SIZE / 2; j++)
				builder.append(" ");
			builder.append(mDataTree.get(data) + "\n");
		}
		return builder.toString();
	}

	public String asRFTable() {
		Iterator<String> itr = mDataTree.keySet().iterator();
		float dataSize = mDataList.size();
		StringBuilder builder = new StringBuilder();
		builder.append(mDataName);
		for(int i = mDataName.length(); i < COLUMN_SIZE; i++)
			builder.append(" ");
		builder.append(" Percentage\n");
		
		
		for(int i = 0; i < COLUMN_SIZE * 2; i++)
			builder.append("-");
		builder.append("\n");
		
		while(itr.hasNext()) {
			String data = itr.next();
			builder.append(data);
			for(int j = data.length(); j < COLUMN_SIZE; j++)
				builder.append(" ");
			builder.append("|");
			for(int j = 0; j < COLUMN_SIZE / 2; j++)
				builder.append(" ");
			builder.append((mDataTree.get(data) / dataSize) * 100 + "%\n");
		}
		return builder.toString();
	}
	
	public String asBothTable() {
		Iterator<String> itr = mDataTree.keySet().iterator();
		float dataSize = mDataList.size();
		StringBuilder builder = new StringBuilder();
		builder.append(mDataName);
		for(int i = mDataName.length(); i < COLUMN_SIZE; i++)
			builder.append(" ");
		builder.append(" Percentage");
		for(int i = "Percentage".length(); i < COLUMN_SIZE; i++)
			builder.append(" ");
		builder.append(" Frequency\n");
		
		
		for(int i = 0; i < COLUMN_SIZE * 3; i++)
			builder.append("-");
		builder.append("\n");
		
		while(itr.hasNext()) {
			String data = itr.next();
			builder.append(data);
			for(int j = data.length(); j < COLUMN_SIZE; j++)
				builder.append(" ");
			builder.append("|");
			for(int j = 0; j < COLUMN_SIZE / 2; j++)
				builder.append(" ");
			int dataCount = mDataTree.get(data);
			builder.append((dataCount / dataSize) * 100 + "%");
			builder.append("|");
			for(int j = 0; j < COLUMN_SIZE / 2; j++)
				builder.append(" ");
			builder.append(dataCount + "\n");
		}
		return builder.toString();		
	}
	
	public Map<String, Integer> getDataTree() {
		return mDataTree;
	}
	
	public int size() {
		return mDataList.size();
	}
}
