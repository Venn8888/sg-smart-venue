package com.sg.framework.util;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sg.framework.exception.BusinessException;

public class FileUtil {
	private static Log logger = LogFactory.getLog(FileUtil.class);
	public static String TYPE_IMAGE = "image";
	public static String TYPE_VEDIO = "vedio";
	public static String TYPE_FILE = "file";
	
	public static String generateFilename(String fileName) {
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str1 = localSimpleDateFormat.format(new Date());
		
		StringBuffer r = new StringBuffer(5);
	    Random random = new Random();
	    for (int i = 0; i < 5; ++i) {
	      r.append(random.nextInt(9));
	    }

		return str1 + r.toString() + "." + getExtention(fileName);
	}
	
	public static String generateFilename(String path, String fileName) {
		fileName = generateFilename(fileName);
		if(StringUtil.isNotEmpty(path)){
			path = DateUtil.getYear() + "/" + path;
			if(path.endsWith("/"))
				fileName = path + fileName;
			else
				fileName = path + "/" + fileName;
		}
		
		return fileName;
	}
	
	
	public static String getExtention(String filename) {
		if ((filename == null) || (filename.equals(""))) {
			return "";
		}
		String ext = "";
		if (filename.indexOf(".") < 0) {
			ext = filename;
		} else {
			int pos = filename.lastIndexOf(46);
			ext = filename.substring(pos + 1);
		}
		return ext;
	}
	
	 public static boolean isImageFile(InputStream inputStream) {
        if (inputStream == null) {
            return false;
        }
        Image img;
        try {
            img = ImageIO.read(inputStream);
            return !(img == null || img.getWidth(null) <= 0 || img.getHeight(null) <= 0);
        } catch (Exception e) {
            return false;
        }
    }
	 
	private final static String PREFIX_VIDEO = "video/";
	/**
	 * Get the Mime Type from a File
	 * @param fileName
	 *            文件名 *
	 * @return 返回MIME类型 *
	 */
	private static String getMimeType(String fileName) {
		FileNameMap fileNameMap = URLConnection.getFileNameMap();
		String type = fileNameMap.getContentTypeFor(fileName);
		return type;
	}

	/**
	 * 根据文件后缀名判断 文件是否是视频文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return 是否是视频文件
	 * */
	public static boolean isVedioFile(String fileName) {
		String mimeType = getMimeType(fileName);
		if (!StringUtil.isEmpty(fileName) && !StringUtil.isEmpty(mimeType) && mimeType.contains(PREFIX_VIDEO)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 新建文件
	 * @param outputFile
	 * @param file
	 */
	public static void createFile(File file, String outputFile) {
	    FileInputStream fis = null;
	    try {
	      fis = new FileInputStream(file);
	      createFile(fis, outputFile);
	    } catch (IOException e) {
			logger.error(e.getMessage());
			throw new BusinessException("创建文件失败！文件：" + outputFile);
		} finally {
	      try {
	        fis.close();
	      } catch (Exception localException) {
	      }
	    }
	}

	/**
	 * 新建文件
	 * @param outputFile
	 * @param inputStream
	 */
	public static void createFile(InputStream inputStream, String outputFile) {
		File toFile = new File(outputFile);
		
		if(!new File(toFile.getParent()).exists())
			createFolder(toFile.getParent());
		
		FileOutputStream fos = null;
		try {
			toFile.createNewFile();
			fos = new FileOutputStream(toFile);

			byte[] buf = new byte[4096];
			int bytesRead;
			while ((bytesRead = inputStream.read(buf)) != -1) {
				fos.write(buf, 0, bytesRead);
			}
			fos.flush();
			buf = (byte[]) null;
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new BusinessException("创建文件失败！文件：" + outputFile);
		} finally {
			try {
				fos.close();
			} catch (Exception localException) {
			}
		}
	}

	public static void createFile(InputStream inputStream,
			File toFile) {
		if(!new File(toFile.getParent()).exists())
			createFolder(toFile.getParent());
		
		FileOutputStream fos = null;
		try {
			toFile.createNewFile();
			fos = new FileOutputStream(toFile);

			byte[] buf = new byte[4096];
			int bytesRead;
			while ((bytesRead = inputStream.read(buf)) != -1) {
				fos.write(buf, 0, bytesRead);
			}
			fos.flush();
			buf = (byte[]) null;
		} catch (IOException e) {
			logger.error(e.getMessage());
			throw new BusinessException("创建文件失败！文件：" + toFile.getName());
		} finally {
			try {
				fos.close();
			} catch (Exception localException) {
			}
		}
	}
	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @param fileContent
	 *            文本文件内容
	 * @return
	 */
	public static void createFile(String outputFile, String content) {
		createFile(outputFile, content, "UTF-8");
	}

	/**
	 * 有编码方式的文件创建
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @param fileContent
	 *            文本文件内容
	 * @param encoding
	 *            编码方式 例如 GBK 或者 UTF-8
	 * @return
	 */
	public static void createFile(String outputFile, String content,
			String encoding) {
		try {
			File toFile = new File(outputFile);
			if(!new File(toFile.getParent()).exists())
				createFolder(toFile.getParent());

			PrintWriter myFile = new PrintWriter(outputFile, encoding);
			myFile.println(content);
			myFile.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("创建文件失败！文件：" + outputFile);
		}
	}

	public static String readFile(String inputFile, String encoding) {
		StringBuffer localStringBuffer = new StringBuffer();
		try {
			File localFile = new File(inputFile);
			FileInputStream localFileInputStream = null;
			BufferedReader localBufferedReader = null;
			try {
				localFileInputStream = new FileInputStream(localFile);
				InputStreamReader localInputStreamReader = new InputStreamReader(
						localFileInputStream, encoding);
				localBufferedReader = new BufferedReader(localInputStreamReader);

				String str;
				while ((str = localBufferedReader.readLine()) != null) {
					localStringBuffer.append(str);
					localStringBuffer.append("\r\n");
				}
				localBufferedReader.close();
				localInputStreamReader.close();
				localFileInputStream.close();
			} catch (FileNotFoundException localFileNotFoundException) {
				logger.error(localFileNotFoundException.getMessage());
				throw new BusinessException("读取文件失败！文件：" + inputFile);
			} catch (IOException localIOException) {
				logger.error(localIOException.getMessage());
				throw new BusinessException("读取文件失败！文件：" + inputFile);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("读取文件失败！文件：" + inputFile);
		}
		return localStringBuffer.toString();
	}

	/**
	 * 读取文本文件内容
	 * 
	 * @param filePathAndName
	 *            带有完整绝对路径的文件名
	 * @param encoding
	 *            文本文件打开的编码方式
	 * @return 返回文本文件的内容
	 */
	public static String readFile(String inputFile) throws IOException {
		return readFile(inputFile, "UTF-8");
	}

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            目录
	 * @return 返回目录创建后的路径
	 */
	public static void createFolder(String path) {
		File f = new File(path);
		File ft = f;
		while (!(f.exists())) {
			ft = f;
			f = f.getParentFile();
		}
		if (f != ft) {
			ft.mkdir();
			createFolder(path);
		}
	}
	  
	public static void writeFile(String message, String path) {
		writeFile(message, path, true);
	}

	public static void writeFile(String message, String path, boolean append) {
		OutputStreamWriter os = null;
		try {
			os = new OutputStreamWriter(new FileOutputStream(path, append),
					"UTF-8");
			os.write((message + System.getProperty("line.separator")));
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 *            文本文件完整绝对路径及文件名
	 * @return Boolean 成功删除返回true遭遇异常返回false
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			File myDelFile = new File(filePath);
			if (myDelFile.exists()) {
				myDelFile.delete();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("删除文件操作出错！文件：" + filePathAndName);
		}
	}

	/**
	 * 删除文件夹
	 * 
	 * @param folderPath
	 *            文件夹完整绝对路径
	 * @return
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("删除文件夹操作出错！目录：" + folderPath);
		}
	}

	/**
	 * 删除指定文件夹下所有文件
	 * 
	 * @param path
	 *            文件夹完整绝对路径
	 * @return
	 * @return
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPathFile
	 *            准备复制的文件源
	 * @param newPathFile
	 *            拷贝到新绝对路径带文件名
	 * @return
	 */
	public static void copyFile(String oldPathFile, String newPathFile) {
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPathFile);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPathFile); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPathFile);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("复制单个文件操作出错！文件：" + oldPathFile);
		}
	}

	/**
	 * 复制整个文件夹的内容
	 * 
	 * @param oldPath
	 *            准备拷贝的目录
	 * @param newPath
	 *            指定绝对路径的新目录
	 * @return
	 */
	public static void copyFolder(String oldPath, String newPath) {
		try {
			new File(newPath).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException("复制整个文件夹内容操作出错！目录：" + oldPath);
		}
	}

	/**
	 * 移动文件
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);
	}

	/**
	 * 移动目录
	 * 
	 * @param oldPath
	 * @param newPath
	 * @return
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);
	}

	
	public static void compress(String srcFilePath, String destFilePath) {
        File src = new File(srcFilePath);
        if (!src.exists()) {
            throw new RuntimeException(srcFilePath + "不存在");
        }

        File zipFile = new File(destFilePath);
        try {
            FileOutputStream fos = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fos, new CRC32());
            ZipOutputStream zos = new ZipOutputStream(cos);

            String baseDir = "";
            compressbyType(src, zos, baseDir);
            zos.close();
        } catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException(e.getMessage());
        }
    }
 

    private static void compressbyType(File src, ZipOutputStream zos,
            String baseDir) {
        if (!src.exists())
            return;

        System.out.println("压缩" + baseDir + src.getName());
        if (src.isFile()) {
            compressFile(src, zos, baseDir);
        } else if (src.isDirectory()) {
            compressDir(src, zos, baseDir);
        }
    }

 

    /**
     * 压缩文件
     *
     */
    private static void compressFile(File file, ZipOutputStream zos,
            String baseDir) {
        if (!file.exists())
            return;

        try {
            BufferedInputStream bis = new BufferedInputStream(
                    new FileInputStream(file));
            ZipEntry entry = new ZipEntry(baseDir + file.getName());
            zos.putNextEntry(entry);
            int count;
            byte[] buf = new byte[1024];
            while ((count = bis.read(buf)) != -1) {
                zos.write(buf, 0, count);
            }
            bis.close();
        } catch (Exception e) {
			logger.error(e.getMessage());
			throw new BusinessException(e.getMessage());
       }
    }
 
    /**
     * 压缩文件夹
     *
     */
    private static void compressDir(File dir, ZipOutputStream zos,
            String baseDir) {
        if (!dir.exists())
            return;
        File[] files = dir.listFiles();
        if(files.length == 0){
            try {
                zos.putNextEntry(new ZipEntry(baseDir + dir.getName()
                        + File.separator));
            } catch (IOException e) {
    			logger.error(e.getMessage());
    			throw new BusinessException(e.getMessage());
            }
        }

        for (File file : files) {
            compressbyType(file, zos, baseDir + dir.getName() + File.separator);
        }
    }
    
//	public static void main(String[] args) {
//		
//		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
//		String str1 = localSimpleDateFormat.format(new Date());
//
//		System.out.println(str1);
//	}

}
