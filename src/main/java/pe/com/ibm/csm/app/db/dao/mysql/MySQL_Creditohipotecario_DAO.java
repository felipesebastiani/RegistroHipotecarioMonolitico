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
import pe.com.ibm.csm.app.db.beans.Bean_Creditohipotecario;
import pe.com.ibm.csm.app.db.dao.Creditohipotecario_DAO;

@Repository
public class MySQL_Creditohipotecario_DAO implements Creditohipotecario_DAO {
	private final static Logger LOGGER = LogManager.getLogger();

	protected ConexionEstandar_FRSS conexion_estandar_frss = null;

	public void setDataSource(DataSource datasource) {
		conexion_estandar_frss = new ConexionEstandar_FRSS();
		conexion_estandar_frss.setDatasource(datasource);

	}

	@Override
	public Bean_Creditohipotecario getBean_Creditohipotecario(String idCre) {
		Bean_Creditohipotecario bean_creditohipotecario = null;

		try{
			String query = "SELECT idCre, idCli, tipoCre, montoInicial, montoFinanciar, plazoMeses, sueldoBruto, costoInmueble, tasa FROM tb_creditohipotecario WHERE idCre = '" + idCre + "'";
			ResultSet rs = this.conexion_estandar_frss.getResultSet(query);
			if(rs.next()){
				bean_creditohipotecario = new Bean_Creditohipotecario();
				bean_creditohipotecario.setIdCre(rs.getString(1));
				bean_creditohipotecario.setIdCli(rs.getString(2));
				bean_creditohipotecario.setTipoCre(rs.getString(3));
				bean_creditohipotecario.setMontoInicial(rs.getDouble(4));
				bean_creditohipotecario.setMontoFinanciar(rs.getDouble(5));
				bean_creditohipotecario.setPlazoMeses(rs.getInt(6));
				bean_creditohipotecario.setSueldoBruto(rs.getDouble(7));
				bean_creditohipotecario.setCostoInmueble(rs.getDouble(8));
				bean_creditohipotecario.setTasa(rs.getDouble(9));
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

		return bean_creditohipotecario;
	}

	@Override
	public Bean_Creditohipotecario getBean_Creditohipotecario_segun_Query(String query) {
		Bean_Creditohipotecario bean_creditohipotecario = null;

		try{
			ResultSet rs = this.conexion_estandar_frss.getResultSet(query);

			if(rs.next()){
				bean_creditohipotecario = new Bean_Creditohipotecario();
				bean_creditohipotecario.setIdCre(rs.getString(1));
				bean_creditohipotecario.setIdCli(rs.getString(2));
				bean_creditohipotecario.setTipoCre(rs.getString(3));
				bean_creditohipotecario.setMontoInicial(rs.getDouble(4));
				bean_creditohipotecario.setMontoFinanciar(rs.getDouble(5));
				bean_creditohipotecario.setPlazoMeses(rs.getInt(6));
				bean_creditohipotecario.setSueldoBruto(rs.getDouble(7));
				bean_creditohipotecario.setCostoInmueble(rs.getDouble(8));
				bean_creditohipotecario.setTasa(rs.getDouble(9));
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

		return bean_creditohipotecario;
	}

	@Override
	public boolean existeBean_Creditohipotecario(String idCre) {
		boolean existe = false;

		if(this.getBean_Creditohipotecario(idCre) != null){
			existe = true;
		}

		return existe;
	}

	@Override
	public boolean insertarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario) {
		boolean exito = false;

		String query = this.getSQLQuery_insertarBean_Creditohipotecario(bean_creditohipotecario);

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();

		return exito;
	}

	@Override
	public boolean actualizarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario) {
		boolean exito = false;

		String query = this.getSQLQuery_actualizarBean_Creditohipotecario(bean_creditohipotecario);

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();

		return exito;
	}

	@Override
	public boolean eliminarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario) {
		boolean exito = false;

		String query = this.getSQLQuery_eliminarBean_Creditohipotecario(bean_creditohipotecario);

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();
		return exito;

	}

	@Override
	public boolean eliminarBean_CreditohipotecarioSegunID(String idCre) {
		boolean exito = false;

		String query = this.getSQLQuery_eliminarBean_Creditohipotecario(getBean_Creditohipotecario(idCre));

		exito = this.conexion_estandar_frss.ejecutaTransaccion_SQL(query);
		this.conexion_estandar_frss.close_conection();
		return exito;

	}

	@Override
	public String getSQLQuery_insertarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario) {
		String query = "";
		query += "INSERT IGNORE INTO tb_creditohipotecario VALUES (";
		query += "'" + bean_creditohipotecario.getIdCre() + "', ";
		query += "'" + bean_creditohipotecario.getIdCli() + "', ";
		query += "'" + bean_creditohipotecario.getTipoCre() + "', ";
		query += "'" + bean_creditohipotecario.getMontoInicial() + "', ";
		query += "'" + bean_creditohipotecario.getMontoFinanciar() + "', ";
		query += "'" + bean_creditohipotecario.getPlazoMeses() + "', ";
		query += "'" + bean_creditohipotecario.getSueldoBruto() + "', ";
		query += "'" + bean_creditohipotecario.getCostoInmueble() + "', ";
		query += "'" + bean_creditohipotecario.getTasa() + "'";
		query += ")";

		return query;
	}


	@Override
	public String getSQLQuery_actualizarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario) {
		String query = "";
		query += "UPDATE IGNORE tb_creditohipotecario SET ";
		query += "idCli = '" + bean_creditohipotecario.getIdCli() + "', ";
		query += "tipoCre = '" + bean_creditohipotecario.getTipoCre() + "', ";
		query += "montoInicial = '" + bean_creditohipotecario.getMontoInicial() + "', ";
		query += "montoFinanciar = '" + bean_creditohipotecario.getMontoFinanciar() + "', ";
		query += "plazoMeses = '" + bean_creditohipotecario.getPlazoMeses() + "', ";
		query += "sueldoBruto = '" + bean_creditohipotecario.getSueldoBruto() + "', ";
		query += "costoInmueble = '" + bean_creditohipotecario.getCostoInmueble() + "', ";
		query += "tasa = '" + bean_creditohipotecario.getTasa() + "' ";
		query += "WHERE " + "idCre = '" + bean_creditohipotecario.getIdCre() + "'";

		return query;
	}

	@Override
	public String getSQLQuery_eliminarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario) {
		String query = "";
		query += "DELETE IGNORE FROM tb_creditohipotecario WHERE ";
		query += "idCre = '" + bean_creditohipotecario.getIdCre() + "'";

		return query;
	}

	@Override
	public String getIdSegunDatos_Bean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario) {
		String idBuscado = null;
		String query = "";
		query += "SELECT idCre FROM tb_creditohipotecario WHERE ";
		query += "idCli = '" + bean_creditohipotecario.getIdCli() + "' ";
		query += "AND tipoCre = '" + bean_creditohipotecario.getTipoCre() + "' ";
		query += "AND montoInicial = '" + bean_creditohipotecario.getMontoInicial() + "' ";
		query += "AND montoFinanciar = '" + bean_creditohipotecario.getMontoFinanciar() + "' ";
		query += "AND plazoMeses = '" + bean_creditohipotecario.getPlazoMeses() + "' ";
		query += "AND sueldoBruto = '" + bean_creditohipotecario.getSueldoBruto() + "' ";
		query += "AND costoInmueble = '" + bean_creditohipotecario.getCostoInmueble() + "' ";
		query += "AND tasa = '" + bean_creditohipotecario.getTasa() + "' ";

		idBuscado = this.conexion_estandar_frss.getValor(query);
		if(idBuscado.equals("")==true) {
			idBuscado = null;
		}
		return idBuscado;
	}

	public ArrayList<Bean_Creditohipotecario> getArrayList_Bean_Creditohipotecario_segun_Query(String query) {
		ArrayList<Bean_Creditohipotecario> arraylist_beans = new ArrayList<Bean_Creditohipotecario>();

		try{
			ResultSet rs = this.conexion_estandar_frss.getResultSet(query);

			while(rs.next()){
				Bean_Creditohipotecario bean_creditohipotecario = new Bean_Creditohipotecario();
				bean_creditohipotecario.setIdCre(rs.getString(1));

				bean_creditohipotecario.setIdCli(rs.getString(2));

				bean_creditohipotecario.setTipoCre(rs.getString(3));

				bean_creditohipotecario.setMontoInicial(rs.getDouble(4));

				bean_creditohipotecario.setMontoFinanciar(rs.getDouble(5));

				bean_creditohipotecario.setPlazoMeses(rs.getInt(6));

				bean_creditohipotecario.setSueldoBruto(rs.getDouble(7));

				bean_creditohipotecario.setCostoInmueble(rs.getDouble(8));

				bean_creditohipotecario.setTasa(rs.getDouble(9));

				arraylist_beans.add(bean_creditohipotecario);
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
	public ArrayList<Bean_Creditohipotecario> getArrayList_Bean_Creditohipotecario_segun_listado_IDs(String[] listado_IDs) {
		ArrayList<Bean_Creditohipotecario> arraylist_beans = new ArrayList<Bean_Creditohipotecario>();

		try{
			for (int i = 0; i < listado_IDs.length; i++) {
				String id = listado_IDs[i];
				Bean_Creditohipotecario bean_creditohipotecario =  getBean_Creditohipotecario(id);
				arraylist_beans.add(bean_creditohipotecario);
			}
		}
		catch (Exception e){
			LOGGER.error("[Exception]Error definido como: " + FR_Util.getStackTraceFromException(e));
		}

		return arraylist_beans;
	}

	@Override
	public boolean ejecuta_lote_consultas_insert_SQL_Bean_Creditohipotecario(ArrayList<Bean_Creditohipotecario> arraylist_bean_creditohipotecario) {
		boolean exito = false;

		ArrayList<String> queries = new ArrayList<String>();

		for (Bean_Creditohipotecario bean_creditohipotecario : arraylist_bean_creditohipotecario) {
			queries.add(this.getSQLQuery_insertarBean_Creditohipotecario(bean_creditohipotecario));
		}

		exito = this.conexion_estandar_frss.ejecutaArrayListTransacciones_SQL(queries);

		return exito;
	}

	@Override
	public boolean ejecuta_lote_consultas_update_SQL_Bean_Creditohipotecario(ArrayList<Bean_Creditohipotecario> arraylist_bean_creditohipotecario) {
		boolean exito = false;

		ArrayList<String> queries = new ArrayList<String>();

		for (Bean_Creditohipotecario bean_creditohipotecario : arraylist_bean_creditohipotecario) {
			queries.add(this.getSQLQuery_actualizarBean_Creditohipotecario(bean_creditohipotecario));
		}

		exito = this.conexion_estandar_frss.ejecutaArrayListTransacciones_SQL(queries);

		return exito;
	}

	@Override
	public boolean ejecuta_lote_consultas_delete_SQL_Bean_Creditohipotecario(ArrayList<Bean_Creditohipotecario> arraylist_bean_creditohipotecario) {
		boolean exito = false;

		ArrayList<String> queries = new ArrayList<String>();

		for (Bean_Creditohipotecario bean_creditohipotecario : arraylist_bean_creditohipotecario) {
			queries.add(this.getSQLQuery_eliminarBean_Creditohipotecario(bean_creditohipotecario));
		}

		exito = this.conexion_estandar_frss.ejecutaArrayListTransacciones_SQL(queries);

		return exito;
	}
}