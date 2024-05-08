package com.mitake.camel.fetnp.dao;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.mitake.camel.utils.CommonUtil;

public class ExportSMCDRResultSetExtractor implements ResultSetExtractor<Object> {
	private final Logger logger = LoggerFactory.getLogger(ExportSMCDRResultSetExtractor.class);

	private String fileName = null;

	public ExportSMCDRResultSetExtractor(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Object extractData(ResultSet rs) {
		File file = null;
		FileWriter fw = null;
		BufferedWriter bw = null;
		int exportCount = 0;
		String destNo = null;

		try {

			if (rs.next()) {
				file = new File(fileName);
				if (file.exists()) {
					file.delete();
				}
				fw = new FileWriter(file, StandardCharsets.UTF_8, false);
				bw = new BufferedWriter(fw, 8192);

				do {

					destNo = rs.getString(1) == null ? ""
							: CommonUtil.getInstance().regularDestNo(StringUtils.trimToNull(rs.getString(1)));
					bw.write(destNo);
					bw.newLine();
					exportCount++;

				} while (rs.next());

				bw.flush();
			}

		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException e) {
				logger.error(ExceptionUtils.getStackTrace(e));
			}
		}
		return exportCount;

	}

}
