package com.dang.common.util.file;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.dang.common.code.ConfigCode;
import com.dang.common.code.ErrorCode;
import com.dang.common.exception.ToAlertException;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

public class FileUtil {

	private final int maxSize = 1024 * 1024 * 10;
	// 파일을 업로드 하고
	// 파일 테이블에 저장할 파일메타정보와 파라미터 값을 저장해서 Map에 담아 return

	// 파라미터가 겹칠 수 있어서 List로 한번더 분리
	// (key운동) 파라미터 -> (value 축구 농구 야구)
	Map<String, List> multiParamMap = new HashMap<String, List>();

	// 파일을 업로드

	// renameFilename 생성

	// 저장경로 생성

	// 저장경로를 생성하고 파일 저장

	// 파일메타정보, 파라미터를 Map에 저장

	// 파라미터 저장

	// 파일메타정보 저장

	///////////////////////////////////////////////////

	// renameFilename 생성
	private String getRenameFileName(String originFileName) {

		// 유니크한 파일명 생성
		UUID renameFileID = UUID.randomUUID();
		String renameFileName = renameFileID.toString()
				// 마지막 .을 기준으로 .뒤의 문자열을 잘라서 반환 : 확장자
				+ originFileName.substring(originFileName.lastIndexOf("."));

		return renameFileName;
	}

	// 저장경로 생성
	private String getSubPath() {

		// 날짜로 저장경로
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DAY_OF_MONTH);

		String subPath = year + "/" + month + "/" + date + "/";
		// 날짜별로 쪼개서 유니크 이름으로 저장

		return subPath;

	}

	private void saveFile(FilePart userFile, FileVo fileData) throws IOException {
		String path = ConfigCode.UPLOAD_PATH + fileData.getSavePath();
		new File(path).mkdirs(); // 수정한 코드
		File file = new File(path + fileData.getRenameFileName()); // 수정한 코드
		userFile.writeTo(file);
	}

	private FileVo getfileData(FilePart userFile) throws UnsupportedEncodingException {

		// 파일 제목 인코딩 문제해결
		String originFileName = new String(userFile.getFileName().getBytes("iso-8859-1"), "UTF-8");

		// 메서드 분리
		String renameFileName = getRenameFileName(originFileName);

		FileVo fileData = new FileVo();
		fileData.setOriginFileName(originFileName);
		fileData.setRenameFileName(renameFileName);
		fileData.setSavePath(getSubPath());
		// 파일Vo에 저장

		return fileData;

	}

	private List<String> getParamValue(ParamPart params) throws UnsupportedEncodingException {
		String paramName = params.getName();
		// 스트링으로 담겨있는 파라미터 반환

		String paramValue = params.getStringValue("UTF-8");

		// 파라미터 이름 중복 가능성 -> List로 만들어놨음
		// 파라미터를 저정할 list
		List<String> paramList;

		// 파라미터를 처음 넣을때
		if (multiParamMap.get(paramName) == null) {
			paramList = new ArrayList<String>();
			paramList.add(paramValue);

		} else {
			// 처음 만든게 아니면 기존에 있던거 뒤에 붙어넣기
			paramList = multiParamMap.get(paramName);
			paramList.add(paramValue);

		}

		multiParamMap.put(paramName, paramList);

		return paramList;

	}

	public Map<String, List> fileUpload(HttpServletRequest request) {

		// 파일메타정보를 저장할 리스트
		List<FileVo> fileDataList = new ArrayList<FileVo>();

		// 두개의 리스트
		// 파일 메티정보 / 파라미터값
		// 파일 part / param part

		MultipartParser mp;

		try {

			mp = new MultipartParser(request, maxSize);
			Part part;

			while ((part = mp.readNextPart()) != null) {
				if (part.isParam()) {
					// param 파트라면
					// 파트에 file / param있음
					// 다운 캐스팅 파트에서 더이상 쓸게 없음
					// param쓸거니까 part -> param으로 다운캐스팅
					ParamPart params = (ParamPart) part;

					List<String> paramList = getParamValue(params);
					multiParamMap.put(params.getName(), paramList);

					// 파일일경우
				} else if (part.isFile()) {

					FilePart userFile = (FilePart) part;

					if (userFile.getFileName() != null) {
						// 업로드된 파일이 존재해야 getFileName이 null이 아니다

						// 파일 테이블에 저장할 파일메타정보를 반환
						FileVo fileData = getfileData(userFile);

						// 파일데이타 리스트에 파일메타정보를 저장
						fileDataList.add(fileData);

						// 파일을 업로드
						saveFile(userFile, fileData);
					}

				}
			}
			multiParamMap.put("fileData", fileDataList);
		} catch (IOException e) {
			throw new ToAlertException(ErrorCode.FILE01, e);

		}

		return multiParamMap;
	}

	public void deleteFile(String path) {
		File file = new File(path);
		file.delete();
	}

}