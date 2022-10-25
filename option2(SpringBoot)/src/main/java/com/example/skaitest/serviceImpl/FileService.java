package com.example.skaitest.serviceImpl;

import com.example.skaitest.gameService.dto.DtoFiles;
import java.util.ArrayList;

public interface FileService {

    void getPathListByFolderPath(String path);

    ArrayList<DtoFiles> getFileDTOList(String path);
}
