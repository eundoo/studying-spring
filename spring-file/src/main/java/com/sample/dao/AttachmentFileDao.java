package com.sample.dao;

import java.util.List;

import com.sample.vo.AttachmentFile;

public interface AttachmentFileDao {

	void insertAttachmentFile(AttachmentFile attachmentFile);
	List<AttachmentFile> getAllAttachmentFiles();
	AttachmentFile getAttachmentFile(long fileNo);
	void updateAttachmentFile(AttachmentFile attachmentFile);
	void deleteAttachmentFile(long fileNo);
}
