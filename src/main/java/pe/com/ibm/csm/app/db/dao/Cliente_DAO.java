package pe.com.ibm.csm.app.db.dao;

import pe.com.ibm.csm.app.db.beans.Bean_Cliente;
import java.util.ArrayList;

public interface Cliente_DAO {

	public abstract Bean_Cliente getBean_Cliente(String idCli);

	public abstract Bean_Cliente getBean_Cliente_segun_Query(String query);

	public abstract boolean existeBean_Cliente(String idCli);

	public abstract boolean insertarBean_Cliente(Bean_Cliente bean_cliente);

	public abstract boolean actualizarBean_Cliente(Bean_Cliente bean_cliente);

	public abstract boolean eliminarBean_Cliente(Bean_Cliente bean_cliente);

	public abstract boolean eliminarBean_ClienteSegunID(String idCli);

	public abstract String getSQLQuery_insertarBean_Cliente(Bean_Cliente bean_cliente);

	public abstract String getSQLQuery_actualizarBean_Cliente(Bean_Cliente bean_cliente);

	public abstract String getSQLQuery_eliminarBean_Cliente(Bean_Cliente bean_cliente);

	public abstract String getIdSegunDatos_Bean_Cliente(Bean_Cliente bean_cliente);

	public abstract ArrayList<Bean_Cliente> getArrayList_Bean_Cliente_segun_Query(String query);

	public abstract ArrayList<Bean_Cliente> getArrayList_Bean_Cliente_segun_listado_IDs(String[] listado_IDs);
}