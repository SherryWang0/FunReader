package com.example.funreader.modle;

public class FileClass
{
	private String name;
	private String path;
	private boolean isInBridView = true;
	private long space;
	private long progress = 0;
	
	public long getSpace()
	{
		return space;
	}
	public void setSpace(long space)
	{
		this.space = space;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public boolean isInBridView()
	{
		return isInBridView;
	}
	public void setInBridView(boolean isInBridView)
	{
		this.isInBridView = isInBridView;
	}
	public long getProgress()
	{
		return progress;
	}
	public void setProgress(int progress)
	{
		this.progress = progress;
	}
}
