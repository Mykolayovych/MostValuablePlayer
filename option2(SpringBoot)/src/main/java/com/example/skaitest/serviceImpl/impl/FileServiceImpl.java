package com.example.skaitest.serviceImpl.impl;

import com.example.skaitest.gameService.dto.DtoFiles;
import com.example.skaitest.serviceImpl.FileService;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {

    private List<Path> pathList = new ArrayList<>();

    @Override
    public void getPathListByFolderPath(String path) {
        try {
            pathList = Files.walk(Paths.get(path)).filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Path is not valid");
        }
    }

    @Override
    public ArrayList<DtoFiles> getFileDTOList(String path) {
        getPathListByFolderPath(path);
        ArrayList<DtoFiles> result = new ArrayList<>();
        pathList.forEach(
                myPath -> {
                    DtoFiles fileDTO = null;
                    try (FileInputStream inputStream = new FileInputStream(myPath.toString());
                         Scanner sc = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                        fileDTO = new DtoFiles();

                        if (sc.hasNextLine()) {
                            fileDTO.setGameName(sc.nextLine());
                        }

                        while (sc.hasNextLine()) {
                            fileDTO.getLines().add(sc.nextLine());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    result.add(fileDTO);
                });

        return result;
    }

}
