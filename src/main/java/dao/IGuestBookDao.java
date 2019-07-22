package dao;

import model.GuestForm;

import java.util.List;

public interface IGuestBookDao {
    List<GuestForm> getAllRecords();
    void insertRecord(String name, String message);
}
