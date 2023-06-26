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
import pe.com.ibm.csm.app.db.beans.Bean_Solicitud;
import pe.com.ibm.csm.app.db.dao.Solicitud_DAO;

@Repository
public class MySQL_Solicitud_DAO implements Solicitud_DAO {
	private final static Logger LOGGER = LogManager.getLogger();

	protected ConexionEstandar_FRSS conexion_estandar_frss = null;

	public void setDataSource(DataSource datasource) {
		conexion_estandar_frss = new ConexionEstandar_FRSS();
		conexion_estandar_frss.setDatasource(datasource);

	}

	@Override
	public Bean_Solicitud getBean_Solicitud(String idSol) {
		Bean_Solicitud bean_solicitud = null;

		try{
			String query = "SELECT idSol, idCre, resultado FROM tb_solicitud WHERE idSol = '" + idSol + "'";
			ResultSet rs = this.conexion_estandar_frss.getResultSet(query);
			if(rs.next()){
				bean_solicitud = new Bean_Solicitud();
				bean_solicitud.setIdSol(rs.getString(1));
				bean_solicitud.setIdCre(rs.getString(2));
				bean_solicitud.setResultado(rs.getString(3));
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

		return bean_solicitud;
	}

	@Override
	public Bean_Solicitud getBean_Solicitud_segun_Query(String query) {
		Bean_Solicitud bean_solicitud = null;

		try{
			ResultSet rs = this.conexion_estandar_frss.getResultSet(query);

			if(rs.next()){
				bean_solicitud = new Bean_Solicitud();
				bean_solicitud.setIdSol(rs.getString(1));
				bean_solicitud.setIdCre(rs.getString(2));
				bean_solicitud.setResultado(rs.getString(3));
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

		return bean_solicitud;
	}

	@Override
	public boolean existeBean_Solicitud(String idSol) {
		boolean existe = false;

		if(this.getBean_Solicitud(idSol) != null){
			existe = true;
		}

		return existe;
	}

	@Override
	public boolean insertarBean_Solicitud(Bean_Solicitud bean_solicitud) {
		boolean exito = false;

		String query = this.getSQLQuery_insertarBean_Solicitud(bean_solicitud);

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();

		return exito;
	}

	@Override
	public boolean actualizarBean_Solicitud(Bean_Solicitud bean_solicitud) {
		boolean exito = false;

		String query = this.getSQLQuery_actualizarBean_Solicitud(bean_solicitud);

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();

		return exito;
	}

	@Override
	public boolean eliminarBean_Solicitud(Bean_Solicitud bean_solicitud) {
		boolean exito = false;

		String query = this.getSQLQuery_eliminarBean_Solicitud(bean_solicitud);

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();
		return exito;

	}

	@Override
	public boolean eliminarBean_SolicitudSegunID(String idSol) {
		boolean exito = false;

		String query = this.getSQLQuery_eliminarBean_Solicitud(getBean_Solicitud(idSol));

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();
		return exito;

	}

	@Override
	public String getSQLQuery_insertarBean_Solicitud(Bean_Solicitud bean_solicitud) {
		String query = "";
		query += "INSERT IGNORE INTO tb_solicitud VALUES (";
		query += "'" + bean_solicitud.getIdSol() + "', ";
		query += "'" + bean_solicitud.getIdCre() + "', ";
		query += "'" + bean_solicitud.getResultado() + "'";
		query += ")";

		return query;
	}


	@Override
	public String getSQLQuery_actualizarBean_Solicitud(Bean_Solicitud bean_solicitud) {
		String query = "";
		query += "UPDATE IGNORE tb_solicitud SET ";
		query += "idCre = '" + bean_solicitud.getIdCre() + "', ";
		query += "resultado = '" + bean_solicitud.getResultado() + "' ";
		query += "WHERE " + "idSol = '" + bean_solicitud.getIdSol() + "'";

		return query;
	}

	@Override
	public String getSQLQuery_eliminarBean_Solicitud(Bean_Solicitud bean_solicitud) {
		String query = "";
		query += "DELETE IGNORE FROM tb_solicitud WHERE ";
		query += "idSol = '" + bean_solicitud.getIdSol() + "'";

		return query;
	}

	@Override
	public String getIdSegunDatos_Bean_Solicitud(Bean_Solicitud bean_solicitud) {
		String idBuscado = null;
		String query = "";
		query += "SELECT idSol FROM tb_solicitud WHERE ";
		query += "idCre = '" + bean_solicitud.getIdCre() + "' ";
		query += "AND resultado = '" + bean_solicitud.getResultado() + "' ";

		idBuscado = this.conexion_estandar_frss.getValor(query);
		if(idBuscado.equals("")==true) {
			idBuscado = null;
		}
		return idBuscado;
	}

	public ArrayList<Bean_Solicitud> getArrayList_Bean_Solicitud_segun_Query(String query) {
		ArrayList<Bean_Solicitud> arraylist_beans = new ArrayList<Bean_Solicitud>();

		try{
			ResultSet rs = this.conexion_estandar_frss.getResultSet(query);

			while(rs.next()){
				Bean_Solicitud bean_solicitud = new Bean_Solicitud();
				bean_solicitud.setIdSol(rs.getString(1));

				bean_solicitud.setIdCre(rs.getString(2));

				bean_solicitud.setResultado(rs.getString(3));

				arraylist_beans.add(bean_solicitud);
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
}