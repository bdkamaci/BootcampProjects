package dao;

import core.Db;
import entity.Brand;
import entity.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelDao {

    private final Connection connection;
    private final BrandDao brandDao = new BrandDao();

    public ModelDao() {
        this.connection = Db.getInstance();
    }

    public Model getById(int id) {
        Model obj = null;
        String query = "SELECT * FROM public.model WHERE model_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public ArrayList<Model> findAll() {
        return this.selectByQuery("SELECT * FROM public.model ORDER BY model_id ASC");
    }

    public ArrayList<Model> getByListBrandId(int brandId) {
        return this.selectByQuery("SELECT * FROM public.model WHERE model_brand_id = " + brandId);
    }

    public ArrayList<Model> selectByQuery(String query) {
        ArrayList<Model> modelList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                modelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return modelList;
    }

    public boolean save(Model model) {
        String query = "INSERT INTO public.model (model_brand_id, model_name, model_type, model_year, model_fuel, model_gear) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, model.getBrand_id());
            pr.setString(2, model.getName());
            pr.setString(3,model.getType().toString());
            pr.setString(4, model.getYear());
            pr.setString(5, model.getFuel().toString());
            pr.setString(6, model.getGear().toString());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Model model) {
        String query = "UPDATE public.model SET model_brand_id = ?, model_name = ?, model_type = ?, model_year = ?, model_fuel = ?, model_gear = ? WHERE model_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, model.getBrand_id());
            pr.setString(2, model.getName());
            pr.setString(3,model.getType().toString());
            pr.setString(4, model.getYear());
            pr.setString(5, model.getFuel().toString());
            pr.setString(6, model.getGear().toString());
            pr.setInt(7, model.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int model_id) {
        String query = "DELETE FROM public.model WHERE model_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, model_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public Model match(ResultSet resultSet) throws SQLException {
        Model model = new Model();
        model.setId(resultSet.getInt("model_id"));
        model.setBrand_id(resultSet.getInt("model_brand_id"));
        model.setBrand(this.brandDao.getById(resultSet.getInt("model_brand_id")));
        model.setName(resultSet.getString("model_name"));
        model.setType(Model.Type.valueOf(resultSet.getString("model_type")));
        model.setYear(resultSet.getString("model_year"));
        model.setFuel(Model.Fuel.valueOf(resultSet.getString("model_fuel")));
        model.setGear(Model.Gear.valueOf(resultSet.getString("model_gear")));
        return model;
    }
}
