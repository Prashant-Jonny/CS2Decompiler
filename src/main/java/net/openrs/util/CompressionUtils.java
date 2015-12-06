package net.openrs.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.tukaani.xz.LZMAInputStream;

/**
 * A class that contains methods to compress and uncompress BZIP2 and GZIP
 * byte arrays.
 * @author Graham
 * @author `Discardedx2
 */
public final class CompressionUtils {

	
	public static byte[] unlzma(byte[] buffer, int size) {
		byte[] result = new byte[size];
		try {
			
			// stick in the uncompressed size
			byte[] temp = new byte[buffer.length + 8];
			System.arraycopy(buffer, 0, temp, 0, 5);
			temp[5] = (byte) (size >>> 0);
			temp[6] = (byte) (size >>> 8);
			temp[7] = (byte) (size >>> 16);
			temp[8] = (byte) (size >>> 24);
			temp[9] = temp[10] = temp[11] = temp[12] = 0;
			System.arraycopy(buffer, 5, temp, 13, buffer.length - 5);
			LZMAInputStream stream = new LZMAInputStream(new ByteArrayInputStream(temp));
			stream.read(result);
			stream.close();
		} catch (IOException ioex) {
			ioex.printStackTrace();
			return null;
		}
		
		return result;
	}
	
	
	/**
	 * Uncompresses a BZIP2 file.
	 * @param bytes The compressed bytes without the header.
	 * @return The uncompressed bytes.
	 * @throws IOException if an I/O error occurs.
	 */
	public static byte[] bunzip2(byte[] bytes) throws IOException {
		/* prepare a new byte array with the bzip2 header at the start */
		byte[] bzip2 = new byte[bytes.length + 4];
        bzip2[0] = 66;
        bzip2[1] = 90;
        bzip2[2] = 104;
        bzip2[3] = 49;
		System.arraycopy(bytes, 0, bzip2, 4, bytes.length);

		InputStream is = new BZip2CompressorInputStream(new ByteArrayInputStream(bzip2));
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				byte[] buf = new byte[4096];
				int len = 0;
				while ((len = is.read(buf, 0, buf.length)) != -1) {
					os.write(buf, 0, len);
				}
			} finally {
				os.close();
			}

			return os.toByteArray();
		} finally {
			is.close();
		}
	}

	/**
	 * Compresses a BZIP2 file.
	 * @param bytes The uncompressed bytes.
	 * @return The compressed bytes without the header.
	 * @throws IOException if an I/O erorr occurs.
	 */
	public static byte[] bzip2(byte[] bytes) throws IOException {
		InputStream is = new ByteArrayInputStream(bytes);
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			OutputStream os = new BZip2CompressorOutputStream(bout, 1);
			try {
				byte[] buf = new byte[4096];
				int len = 0;
				while ((len = is.read(buf, 0, buf.length)) != -1) {
					os.write(buf, 0, len);
				}
			} finally {
				os.close();
			}

			/* strip the header from the byte array and return it */
			bytes = bout.toByteArray();
			byte[] bzip2 = new byte[bytes.length - 2];
			System.arraycopy(bytes, 2, bzip2, 0, bzip2.length);
			return bzip2;
		} finally {
			is.close();
		}
	}

	/**
	 * Uncompresses a GZIP file.
	 * @param bytes The compressed bytes.
	 * @return The uncompressed bytes.
	 * @throws IOException if an I/O error occurs.
	 */
	public static byte[] gunzip(byte[] bytes) throws IOException {
		/* create the streams */
		InputStream is = new GZIPInputStream(new ByteArrayInputStream(bytes));
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			try {
				/* copy data between the streams */
				byte[] buf = new byte[4096];
				int len = 0;
				while ((len = is.read(buf, 0, buf.length)) != -1) {
					os.write(buf, 0, len);
				}
			} finally {
				os.close();
			}

			/* return the uncompressed bytes */
			return os.toByteArray();
		} finally {
			is.close();
		}
	}

	/**
	 * Compresses a GZIP file.
	 * @param bytes The uncompressed bytes.
	 * @return The compressed bytes.
	 * @throws IOException if an I/O error occurs.
	 */
	public static byte[] gzip(byte[] bytes) throws IOException {
		/* create the streams */
		InputStream is = new ByteArrayInputStream(bytes);
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			OutputStream os = new GZIPOutputStream(bout);
			try {
				/* copy data between the streams */
				byte[] buf = new byte[4096];
				int len = 0;
				while ((len = is.read(buf, 0, buf.length)) != -1) {
					os.write(buf, 0, len);
				}
			} finally {
				os.close();
			}

			/* return the compressed bytes */
			return bout.toByteArray();
		} finally {
			is.close();
		}
	}
	
	/**
	 * Default private constructor to prevent instantiation.
	 */
	private CompressionUtils() {
		
	}

}
