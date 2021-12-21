package dao;

public class DAOFactory {
    private static IDAO dao = null;

    public static IDAO getDAOInstance(TDAO type) {
        if (type == TDAO.MySQL) {
            if (dao == null) {
                dao = new MySQLDAO();
            }
        }
        if (type == TDAO.COLLECTION) {

            dao = new CollectionLDAO();

        }
        return dao;
    }
}
