package com.jeecms.common.upload;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * 本地文件存储
 */
public class FileRepository implements ServletContextAware {
	private Logger log = LoggerFactory.getLogger(FileRepository.class);

	public String storeByExt(String path, String ext, MultipartFile file)
			throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		File dest = new File(getRealPath(filename));
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		return filename;
	}

	public String storeByFilename(String filename, MultipartFile file)
			throws IOException {
		if(filename!=null&&(filename.contains("/")||filename.contains("\\")||filename.indexOf("\0")!=-1)){
			return "";
		}
		File dest = new File(getRealPath(filename));
		store(file, dest);
		return filename;
	}
	
	public String storeByFilePath(String path,String filename, MultipartFile file)
			throws IOException {
		if(filename!=null&&(filename.contains("/")||filename.contains("\\")||filename.indexOf("\0")!=-1)){
			return "";
		}
		File dest = new File(getRealPath(path+filename));
		store(file, dest);
		return path+filename;
	}

	public String storeByExt(String path, String ext, File file)
			throws IOException {
		String filename = UploadUtils.generateFilename(path, ext);
		File dest = new File(getRealPath(filename));
		dest = UploadUtils.getUniqueFile(dest);
		store(file, dest);
		return filename;
	}

	public String storeByFilename(String filename, File file)
			throws IOException {
		File dest = new File(getRealPath(filename));
		store(file, dest);
		return filename;
	}
//上传类型图片走这
	private void store(MultipartFile file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());
			
			InputStream isFile = file.getInputStream();
//			Thumbnails.of(isFile).size(530, 400).sourceRegion(Positions.CENTER, 530, 400).toFile(dest);
			Thumbnails.of(isFile).scale(1f).toFile(dest);
			pressPic(dest,530,400,dest);
//			file.transferTo(dest);
		} catch (IOException e) {
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}
	//压缩至指定图片尺寸，保持图片不变形，多余部分裁剪掉
		public static void pressPic(File file,int width,int higth,File to) throws IOException{
			BufferedImage image = ImageIO.read(file);  
			Builder<BufferedImage> builder = null;  
			  
			int imageWidth = image.getWidth();  
			int imageHeitht = image.getHeight();  
			if ((float)higth / width != (float)imageWidth / imageHeitht) {  
			    if (imageWidth > imageHeitht) {  
			        image = Thumbnails.of(file).height(higth).asBufferedImage();  
			    } else {  
			        image = Thumbnails.of(file).width(width).asBufferedImage();  
			    }  
			    builder = Thumbnails.of(image).sourceRegion(Positions.CENTER, width, higth).size(width, higth);  
			} else {  
			    builder = Thumbnails.of(image).size(width, higth);  
			}  
			builder.toFile(to); 
		}
//上传内容图片和附件图片的时候走这
	private void store(File file, File dest) throws IOException {
		try {
			UploadUtils.checkDirAndCreate(dest.getParentFile());
//			Thumbnails.of(file).size(600, 500).toFile(dest);
			log.info("&&&&"+getRealPath("/r/cms/www/zgkuixun.png"));
			Thumbnails.of(file).size(550, 500).watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(getRealPath("/r/cms/www/zgkuixun.png"))), 0.5f).outputQuality(1f).toFile(dest);
//			FileUtils.copyFile(file, dest);
		} catch (IOException e) {
			log.error("Transfer file error when upload file", e);
			throw e;
		}
	}

	public File retrieve(String name) {
		return new File(ctx.getRealPath(name));
	}
	
	private String getRealPath(String name){
		String realpath=ctx.getRealPath(name);
		if(realpath==null){
			realpath=ctx.getRealPath("/")+name;
		}
		return realpath;
	}

	private ServletContext ctx;

	public void setServletContext(ServletContext servletContext) {
		this.ctx = servletContext;
	}
}
