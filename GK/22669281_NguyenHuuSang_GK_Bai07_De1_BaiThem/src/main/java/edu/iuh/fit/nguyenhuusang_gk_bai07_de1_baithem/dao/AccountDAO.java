package edu.iuh.fit.nguyenhuusang_gk_bai07_de1_baithem.dao;

import edu.iuh.fit.nguyenhuusang_gk_bai07_de1_baithem.Until.DBUntil;
import edu.iuh.fit.nguyenhuusang_gk_bai07_de1_baithem.model.Account;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Dự án: 22669281_NguyenHuuSang_GK_Bai07_De1_BaiThem
 * @Class: AccountDAO
 * @Tạo vào ngày: 9/29/2025
 * @Tác giả: Nguyen Huu Sang
 */
public class AccountDAO {
    private DBUntil dbUntil;

    public AccountDAO(DataSource dataSource) {
        dbUntil = new DBUntil(dataSource);
    }

    public void addAccount(Account account) {
        if(account.getCard_number() ==  null || account.getAccount_number().isEmpty()) {
            account.setAccount_number(java.util.UUID.randomUUID().toString());
        }
        String sql = "INSERT INTO account (account_number, owner_name, card_number, owner_address, admount) VALUES(?,?,?,?,?)";
        try(Connection connection = dbUntil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, account.getAccount_number());
            preparedStatement.setString(2, account.getOwner_name());
            preparedStatement.setString(3, account.getCard_number());
            preparedStatement.setString(4, account.getOwner_address());
            preparedStatement.setDouble(5, account.getAmount());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Account> getAll () {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account";
        try(Connection connection = dbUntil.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                String id = resultSet.getString("account_number");
                String name = resultSet.getString("owner_name");
                String card = resultSet.getString("card_number");
                String address = resultSet.getString("owner_address");
                Double amount = resultSet.getDouble("amount");

                Account account = new Account(id,name, card, address, amount);
                accounts.add(account);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }

    public List<Account> filterAccountByBalance(double b1, double b2) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account WHERE amount >= ? AND amount <= ?";
        try(Connection connection = dbUntil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, b1);
            preparedStatement.setDouble(2, b2);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("account_number");
                String name = resultSet.getString("owner_name");
                String card = resultSet.getString("card_number");
                String address = resultSet.getString("owner_address");
                Double amount = resultSet.getDouble("amount");

                Account account = new Account(id,name, card, address, amount);
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  accounts;
    }

    public List<Account> filterAccountByAdd(String address) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account WHERE owner_address LIKE ?";
        try(Connection connection = dbUntil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + address + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("account_number");
                String name = resultSet.getString("owner_name");
                String card = resultSet.getString("card_number");
                String addressSQL = resultSet.getString("owner_address");
                Double amount = resultSet.getDouble("amount");

                Account account = new Account(id,name, card, addressSQL, amount);
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }


    public List<Account> filterAccountByName(String name) {
        List<Account> accounts = new ArrayList<>();
        String sql = "SELECT * FROM account WHERE owner_name LIKE ?";
        try(Connection connection = dbUntil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + name + "%");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("account_number");
                String nameOut = resultSet.getString("owner_name");
                String card = resultSet.getString("card_number");
                String addressSQL = resultSet.getString("owner_address");
                Double amount = resultSet.getDouble("amount");

                Account account = new Account(id,nameOut, card, addressSQL, amount);
                accounts.add(account);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return accounts;
    }
}