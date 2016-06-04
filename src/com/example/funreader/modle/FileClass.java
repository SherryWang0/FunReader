package com.example.funreader.modle;

import java.io.Serializable;

import android.support.v4.os.ParcelableCompat;

public class FileClass implements Serializable
{
	private String name;
	private String path;
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
	public long getProgress()
	{
		return progress;
	}
	public void setProgress(long progress)
	{
		this.progress = progress;
	}
	public String toString()
	{
		return "文件名：" + getName() + "\n路径：" + getPath() + "\n大小：" + getSpace();
	}
}
