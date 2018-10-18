package net.ebuy.apiapp.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.joda.time.format.DateTimeFormat;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import net.ebuy.apiapp.utils.Utils;

@SuppressWarnings("unused")
public class Utils {
	public static String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		System.out.println(dtf.format(localDate)); // 2016/11/16
		return dtf.format(localDate);
	}
	
	public static String forgotPasswordMessage(String verificationCode) {
		String mesage="Yêu cầu của bạn đã được chấp nhận. Mã xác thực của bạn là: "+verificationCode+". Vui lòng nhập mã xác thực này tại giao diện của phần mềm để hoàn tất quá trinh";
		return mesage;
	}

	
	public static boolean createFolder(String path) {
		File theDir = new File(path);
		if (!theDir.exists()) {
			boolean result = false;

			try {
				System.out.println("Begin make dir: " + path);
				result = theDir.mkdir();
			} catch (Exception se) {
				result = false;
				se.printStackTrace();
			}
			return result;
		} else {
			return true;
		}
	}

	public static void zip(List<String> fileList, String output) {
		try {

			// create byte buffer
			byte[] buffer = new byte[1024];

			FileOutputStream fos = new FileOutputStream(output);

			ZipOutputStream zos = new ZipOutputStream(fos);

			for (String file : fileList) {

				File srcFile = new File(file);

				FileInputStream fis = new FileInputStream(srcFile);

				// begin writing a new ZIP entry, positions the stream to the start of the entry
				// data
				zos.putNextEntry(new ZipEntry(srcFile.getName()));

				int length;

				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}

				zos.closeEntry();

				// close the InputStream
				fis.close();

			}

			// close the ZipOutputStream
			zos.close();

		} catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
		}
	}

	public static String readHtmlFromFile(String filePath) {
		try {
			InputStream is = new FileInputStream(filePath);
			BufferedReader buf = new BufferedReader(new InputStreamReader(is));

			String line = buf.readLine();
			StringBuilder sb = new StringBuilder();

			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}

			return sb.toString();
		} catch (IOException ioe) {
			System.out.println("Error creating zip file: " + ioe);
			return "";
		}
	}

	public static String stringVietnameseMoneyFormatWithFloat(float amount) {
		if (amount <= 1000) {
			return String.format("%@vnđ", amount);
		}
		try {
			NumberFormat formatter = new DecimalFormat("###,###");
			String resp = formatter.format(amount);
			resp = resp.replaceAll(",", ".");
			return String.format("%@vnđ", resp);
		} catch (Exception e) {
			return "0vnđ";
		}

	}
	
	public static String createRandomString(int length) {
		String stringRanger = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    return new SecureRandom()
	            .ints(length, 0, stringRanger.length())
	            .mapToObj(stringRanger::charAt)
	            .map(Object::toString)
	            .collect(Collectors.joining());
	}
	
	public static String encodePassword(String password) {
		ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);
		String hashedPassword = passwordEncoder.encodePassword(password, null);
		return hashedPassword;
	}
	
	public static String decodePassword(String hashPassword) {
		return "";
	}
	
	public static String encodeBase64String(String s) {
		byte[] encodedBytes = Base64.getEncoder().encode(s.getBytes());
		return new String(encodedBytes);
	}
	
	public static String decodeBase64String(String s) {
		byte[] decodedBytes = Base64.getDecoder().decode(s);
		return new String(decodedBytes);
	}
	
	public static double roundDouble(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public static String getYearFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("yyyy").format(date));
		} else {
			return (new SimpleDateFormat("yyyy").format(date));
		}
	}
	
	public static String getMonthYearFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("MM/yyyy").format(date));
		} else {
			return (new SimpleDateFormat("MM/yyyy").format(date));
		}
	}
	
	public static String getDateHourFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("dd/MM/yyyy HH:00").format(date));
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:00").format(date));
		}
	}
	
	public static String getDateFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy").format(date));
		}
	}
	
	public static String getDatetimeFormatVN(Date date) {
		if(date == null) {
			date = new Date();
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		} else {
			return (new SimpleDateFormat("dd/MM/yyyy HH:mm").format(date));
		}
		
	}
	
	public static Date getDateWithoutTime(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    return cal.getTime();
	}
	
	public static Date getTomorrowDate(Date date) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.add(Calendar.DATE, 1);
	    return cal.getTime();
	}
	
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();   // this takes current date
	    c.set(Calendar.DAY_OF_MONTH, 1);
	    return c.getTime();
	}
	
	public static String removeAllNoneNumberic(String s) {
		if(s == null) {
			return "";
		} else {
			return s.replaceAll("[^\\d.]", "");
		}
		
	}
	
	public static String saveUploadedFile(MultipartFile image, String resourcePath, String filePath) {
		String imageUrl = "";
		if (!image.isEmpty()) {
			try {
				createFolder(resourcePath + filePath);
				byte[] bytes = image.getBytes();
				filePath = filePath + Calendar.getInstance().getTimeInMillis() + image.getOriginalFilename();
				String fPath = resourcePath + filePath;
				Path path = Paths.get(fPath);
				Files.write(path, bytes);
				imageUrl = filePath;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return imageUrl;
	}

}
