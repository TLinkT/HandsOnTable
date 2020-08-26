package com.a3infortech.importfiles.infra;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.a3infortech.importfiles.services.ImportService;

@Component
public class Retangulo implements ApplicationListener<ApplicationReadyEvent>{
	
	@Autowired
	private ImportService importService;
	
	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		try {
			byte[] file = Files.readAllBytes(new File("C:\\Users\\Levyson\\Documents\\sts-workspace\\prevmedimport\\Arquivos de Teste\\0330 - SANTA RITA.PDF").toPath());
			System.out.println("ProcessPDF");
			importService.processaPdf(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
