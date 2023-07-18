package com.sb_kh.week1.service;

import com.sb_kh.week1.aspect.annotations.Retry;
import com.sb_kh.week1.dto.StudentCentralDataDTO;
import com.sb_kh.week1.exception.NetworkErrorException;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MockedService {

    private Random random = new Random();

    @Retry
    public StudentCentralDataDTO freelyUsedSemesters(final int studentCentralId) throws InterruptedException {
        System.out.println("Request for student : " + studentCentralId + " in progress...");
        Thread.sleep(5000l);
        if (random.nextInt() % 2 != 0) {
            switch (studentCentralId) {
                case 1 : return new StudentCentralDataDTO(studentCentralId, 2);
                case 2 : return new StudentCentralDataDTO(studentCentralId, 3);
                case 3 : return new StudentCentralDataDTO(studentCentralId, 4);
                case 4 : return new StudentCentralDataDTO(studentCentralId, 1);
                default: return new StudentCentralDataDTO(studentCentralId, 0);
            }
        }
        else {
            throw new NetworkErrorException("Network error");
        }
    }

}
