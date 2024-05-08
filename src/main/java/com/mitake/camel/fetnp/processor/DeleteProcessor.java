package com.mitake.camel.fetnp.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.io.File;
import java.util.Collection;
import java.util.Date;

public class DeleteProcessor implements Processor {
    private final static Logger logger = LoggerFactory
            .getLogger(DeleteProcessor.class);
    private int expiredDays = 4;
    private String deletePath;

    @Override
    public void process(Exchange ech) throws Exception {
        Collection<File> found = null;
        int fileCount = 0;
        int dirCount = 0;
        StopWatch sw = new StopWatch("DeleteProcessor");

        if (new File(deletePath).exists()) {
            found = FileUtils.listFilesAndDirs(new File(deletePath),
                    new DeleteFileFilter(), DirectoryFileFilter.INSTANCE);
            // 刪除過期檔案
            sw.start("刪除過期檔案");
            for (File file : found) {
                if (!file.isDirectory()) {
                    fileCount++;
                    FileUtils.deleteQuietly(file);
                }
            }
            sw.stop();

            // 刪除空的資料夾
            sw.start("刪除空資料夾");
            for (File file : found) {
                if (file.isDirectory() && file.list().length == 0
                        && !new File(deletePath).getAbsolutePath()
                        .equals(file.getAbsolutePath())) {
                    dirCount++;
                    FileUtils.deleteQuietly(file);
                }
            }
            sw.stop();
            logger.info("刪除{}天前檔案、空資料夾: fileCount={}, dirCount={}, run time={}",
                    expiredDays, fileCount, dirCount, sw);
        }
    }

    public class DeleteFileFilter implements IOFileFilter {
        @Override
        public boolean accept(File file) {
            Date lastModified = new Date(file.lastModified());
            Date now = new Date();

            if (now.after(DateUtils.addDays(lastModified, expiredDays))) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public boolean accept(File dir, String name) {
            return false;
        }

    }

    public int getExpiredDays() {
        return expiredDays;
    }

    public void setExpiredDays(int expiredDays) {
        this.expiredDays = expiredDays;
    }

    public String getDeletePath() {
        return deletePath;
    }

    public void setDeletePath(String deletePath) {
        this.deletePath = deletePath;
    }
}
