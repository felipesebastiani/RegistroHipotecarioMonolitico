package pe.com.ibm.csm.app.db.dao;

import pe.com.ibm.csm.app.db.beans.Bean_Creditohipotecario;
import java.util.ArrayList;

public interface Creditohipotecario_DAO {

	public abstract Bean_Creditohipotecario getBean_Creditohipotecario(String idCre);

	public abstract Bean_Creditohipotecario getBean_Creditohipotecario_segun_Query(String query);

	public abstract boolean existeBean_Creditohipotecario(String idCre);

	public abstract boolean insertarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario);

	public abstract boolean actualizarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario);

	public abstract boolean eliminarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario);

	public abstract boolean eliminarBean_CreditohipotecarioSegunID(String idCre);

	public abstract String getSQLQuery_insertarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario);

	public abstract String getSQLQuery_actualizarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario);

	public abstract String getSQLQuery_eliminarBean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario);

	public abstract String getIdSegunDatos_Bean_Creditohipotecario(Bean_Creditohipotecario bean_creditohipotecario);

	public abstract ArrayList<Bean_Creditohipotecario> getArrayList_Bean_Creditohipotecario_segun_Query(String query);

	public abstract ArrayList<Bean_Creditohipotecario> getArrayList_Bean_Creditohipotecario_segun_listado_IDs(String[] listado_IDs);

	public abstract boolean ejecuta_lote_consultas_insert_SQL_Bean_Creditohipotecario(ArrayList<Bean_Creditohipotecario> arraylist_bean_creditohipotecario);

	public abstract boolean ejecuta_lote_consultas_update_SQL_Bean_Creditohipotecario(ArrayList<Bean_Creditohipotecario> arraylist_bean_creditohipotecario);

	public abstract boolean ejecuta_lote_consultas_delete_SQL_Bean_Creditohipotecario(ArrayList<Bean_Creditohipotecario> arraylist_bean_creditohipotecario);

}