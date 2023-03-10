<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/views/common/header.jsp" %>

  <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>


  <div class="content-wrapper">

    <section class="content-header">
      <h1>
        Ajouter une voiture
      </h1>
    </section>


    <section class="content">
      <div class="row">
        <div class="col-md-12">

          <div class="box">

            <form class="form-horizontal" method="post">
              <div class="box-body">
                <div class="form-group">
                  <label for="manufacturer" class="col-sm-2 control-label">Marque</label>


                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="manufacturer" name="manufacturer" placeholder="Marque" required>
                  </div>
                </div>
                <div class="form-group">
                  <label for="modele" class="col-sm-2 control-label">Modele</label>

                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="modele" name="modele" placeholder="Modele" required>
                  </div>
                </div>
                <div class="form-group">
                  <label for="seats" class="col-sm-2 control-label">Nombre de places</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" id="seats" name="seats" placeholder="Nombre de places" required>
                  </div>
                </div>
              </div>
              <div class="box-footer">
                <button type="submit" class="btn btn-info pull-right">Modifier</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </section>
  </div>
  <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
