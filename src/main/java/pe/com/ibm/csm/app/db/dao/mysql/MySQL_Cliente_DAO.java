package pe.com.ibm.csm.app.db.dao.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import frss.sql.ConexionEstandar_FRSS;
import frss.util.FR_Util;

import pe.com.ibm.csm.app.db.beans.Bean_Cliente;
import pe.com.ibm.csm.app.db.dao.Cliente_DAO;

@Repository
public class MySQL_Cliente_DAO implements Cliente_DAO {
	private final static Logger LOGGER = LogManager.getLogger();

	protected ConexionEstandar_FRSS conexion_estandar_frss = null;

	public void setDataSource(DataSource datasource) {
		conexion_estandar_frss = new ConexionEstandar_FRSS();
		conexion_estandar_frss.setDatasource(datasource);
	}

	@Override
	public Bean_Cliente getBean_Cliente(String idCli) {
		Bean_Cliente bean_cliente = null;

		try{
			String query = "SELECT idCli, nombres, apellidoPat, apellidoMat, tipo, email, telefono, genero FROM tb_cliente WHERE idCli = '" + idCli + "'";
			ResultSet rs = this.conexion_estandar_frss.getResultSet(query);
			if(rs.next()){
				bean_cliente = new Bean_Cliente();
				bean_cliente.setIdCli(rs.getString(1));
				bean_cliente.setNombres(rs.getString(2));
				bean_cliente.setApellidoPat(rs.getString(3));
				bean_cliente.setApellidoMat(rs.getString(4));
				bean_cliente.setTipo(rs.getString(5));
				bean_cliente.setEmail(rs.getString(6));
				bean_cliente.setTelefono(rs.getString(7));
				bean_cliente.setGenero(rs.getString(8));
			}

		}
		catch (SQLException e){
			LOGGER.error("[SQLException]Error definido como: " + FR_Util.getStackTraceFromException(e));
		}
		catch (Exception e){
			LOGGER.error("[Exception]Error definido como: " + FR_Util.getStackTraceFromException(e));
		}
		finally {
			this.conexion_estandar_frss.close_conection();
		}

		return bean_cliente;
	}

	@Override
	public Bean_Cliente getBean_Cliente_segun_Query(String query) {
		Bean_Cliente bean_cliente = null;

		try{
			ResultSet rs = this.conexion_estandar_frss.getResultSet(query);

			if(rs.next()){
				bean_cliente = new Bean_Cliente();
				bean_cliente.setIdCli(rs.getString(1));
				bean_cliente.setNombres(rs.getString(2));
				bean_cliente.setApellidoPat(rs.getString(3));
				bean_cliente.setApellidoMat(rs.getString(4));
				bean_cliente.setTipo(rs.getString(5));
				bean_cliente.setEmail(rs.getString(6));
				bean_cliente.setTelefono(rs.getString(7));
				bean_cliente.setGenero(rs.getString(8));
			}
		}
		catch (SQLException e){
			LOGGER.error("[SQLException]Error definido como: " + FR_Util.getStackTraceFromException(e));
		}
		catch (Exception e){
			LOGGER.error("[Exception]Error definido como: " + FR_Util.getStackTraceFromException(e));
		}
		finally {
			this.conexion_estandar_frss.close_conection();
		}

		return bean_cliente;
	}

	@Override
	public boolean existeBean_Cliente(String idCli) {
		boolean existe = false;

		if(this.getBean_Cliente(idCli) != null){
			existe = true;
		}

		return existe;
	}

	@Override
	public boolean insertarBean_Cliente(Bean_Cliente bean_cliente) {
		boolean exito = false;

		String query = this.getSQLQuery_insertarBean_Cliente(bean_cliente);

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();

		return exito;
	}

	@Override
	public boolean actualizarBean_Cliente(Bean_Cliente bean_cliente) {
		boolean exito = false;

		String query = this.getSQLQuery_actualizarBean_Cliente(bean_cliente);

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();

		return exito;
	}

	@Override
	public boolean eliminarBean_Cliente(Bean_Cliente bean_cliente) {
		boolean exito = false;

		String query = this.getSQLQuery_eliminarBean_Cliente(bean_cliente);

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();
		return exito;

	}

	@Override
	public boolean eliminarBean_ClienteSegunID(String idCli) {
		boolean exito = false;

		String query = this.getSQLQuery_eliminarBean_Cliente(getBean_Cliente(idCli));

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();
		return exito;

	}

	@Override
	public String getSQLQuery_insertarBean_Cliente(Bean_Cliente bean_cliente) {
		String query = "";
		query += "INSERT IGNORE INTO tb_cliente VALUES (";
		query += "'" + bean_cliente.getIdCli() + "', ";
		query += "'" + bean_cliente.getNombres() + "', ";
		query += "'" + bean_cliente.getApellidoPat() + "', ";
		query += "'" + bean_cliente.getApellidoMat() + "', ";
		query += "'" + bean_cliente.getTipo() + "', ";

		if(bean_cliente.getEmail() != null){
			query += "'" + bean_cliente.getEmail() + "', ";
		}
		else{
			query += "NULL, ";
		}


		if(bean_cliente.getTelefono() != null){
			query += "'" + bean_cliente.getTelefono() + "', ";
		}
		else{
			query += "NULL, ";
		}

		query += "'" + bean_cliente.getGenero() + "'";
		query += ")";

		return query;
	}


	@Override
	public String getSQLQuery_actualizarBean_Cliente(Bean_Cliente bean_cliente) {
		String query = "";
		query += "UPDATE IGNORE tb_cliente SET ";
		query += "nombres = '" + bean_cliente.getNombres() + "', ";
		query += "apellidoPat = '" + bean_cliente.getApellidoPat() + "', ";
		query += "apellidoMat = '" + bean_cliente.getApellidoMat() + "', ";
		query += "tipo = '" + bean_cliente.getTipo() + "', ";
		if(bean_cliente.getEmail() != null){
			query += "email = '" + bean_cliente.getEmail() + "', ";
		}
		else{
			query += "email = NULL, ";
		}

		if(bean_cliente.getTelefono() != null){
			query += "telefono = '" + bean_cliente.getTelefono() + "', ";
		}
		else{
			query += "telefono = NULL, ";
		}

		query += "genero = '" + bean_cliente.getGenero() + "' ";
		query += "WHERE " + "idCli = '" + bean_cliente.getIdCli() + "'";

		return query;
	}

	@Override
	public String getSQLQuery_eliminarBean_Cliente(Bean_Cliente bean_cliente) {
		String query = "";
		query += "DELETE IGNORE FROM tb_cliente WHERE ";
		query += "idCli = '" + bean_cliente.getIdCli() + "'";

		return query;
	}

	@Override
	public String getIdSegunDatos_Bean_Cliente(Bean_Cliente bean_cliente) {
		String idBuscado = null;
		String query = "";
		query += "SELECT idCli FROM tb_cliente WHERE ";
		query += "nombres = '" + bean_cliente.getNombres() + "' ";
		query += "AND apellidoPat = '" + bean_cliente.getApellidoPat() + "' ";
		query += "AND apellidoMat = '" + bean_cliente.getApellidoMat() + "' ";
		query += "AND tipo = '" + bean_cliente.getTipo() + "' ";
		query += "AND email = '" + bean_cliente.getEmail() + "' ";
		query += "AND telefono = '" + bean_cliente.getTelefono() + "' ";
		query += "AND genero = '" + bean_cliente.getGenero() + "' ";

		idBuscado = this.conexion_estandar_frss.getValor(query);
		if(idBuscado.equals("")==true) {
			idBuscado = null;
		}
		return idBuscado;
	}

	public ArrayList<Bean_Cliente> getArrayList_Bean_Cliente_segun_Query(String query) {
		ArrayList<Bean_Cliente> arraylist_beans = new ArrayList<Bean_Cliente>();

		try{
			ResultSet rs = this.conexion_estandar_frss.getResultSet(query);

			while(rs.next()){
				Bean_Cliente bean_cliente = new Bean_Cliente();
				bean_cliente.setIdCli(rs.getString(1));

				bean_cliente.setNombres(rs.getString(2));

				bean_cliente.setApellidoPat(rs.getString(3));

				bean_cliente.setApellidoMat(rs.getString(4));

				bean_cliente.setTipo(rs.getString(5));

				bean_cliente.setEmail(rs.getString(6));

				bean_cliente.setTelefono(rs.getString(7));

				bean_cliente.setGenero(rs.getString(8));

				arraylist_beans.add(bean_cliente);
			}

			this.conexion_estandar_frss.close_conection();
		}
		catch (SQLException e){
			LOGGER.error("[SQLException]Error definido como: " + FR_Util.getStackTraceFromException(e));
		}
		catch (Exception e){
			LOGGER.error("[Exception]Error definido como: " + FR_Util.getStackTraceFromException(e));
		}
		finally {
			this.conexion_estandar_frss.close_conection();
		}

		return arraylist_beans;
	}

	@Override
	public ArrayList<Bean_Cliente> getArrayList_Bean_Cliente_segun_listado_IDs(String[] listado_IDs) {
		ArrayList<Bean_Cliente> arraylist_beans = new ArrayList<Bean_Cliente>();

		try{
			for (int i = 0; i < listado_IDs.length; i++) {
				String id = listado_IDs[i];
				Bean_Cliente bean_cliente =  getBean_Cliente(id);
				arraylist_beans.add(bean_cliente);
			}
		}
		catch (Exception e){
			LOGGER.error("[Exception]Error definido como: " + FR_Util.getStackTraceFromException(e));
		}

		return arraylist_beans;
	}
}