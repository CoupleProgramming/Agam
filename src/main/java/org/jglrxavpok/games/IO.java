package org.jglrxavpok.games;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class IO
{
	
	/**
	 * <font color="red">WARNING: Doesn't close streams!</font>
	 * @param is
	 * @param os
	 * @param bufferLength
	 * @throws IOException
	 */
	public static void copy(InputStream is, OutputStream os, int bufferLength) throws IOException
	{
		int i = 0;
		byte[] buffer = new byte[bufferLength];
		while((i = is.read(buffer, 0, buffer.length)) != -1)
		{
			os.write(buffer,0,i);
		}
	}
	
	public static void copy(InputStream in, String output) throws FileNotFoundException, IOException
	{
		copy(in, new BufferedOutputStream(new FileOutputStream(output)), 65565);
	}

	public static byte[] read(InputStream is)
	{
		try
		{
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[65565];
			while(is.read(buffer, 0, buffer.length) != -1)
			{
				out.write(buffer);
			}
			out.close();
			return out.toByteArray();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
