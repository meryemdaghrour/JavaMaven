<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Main content -->
    <section class="content">

      <div class="row">
        <div class="col-md-3">

          <!-- Profile Image -->
          <div class="box box-primary">
            <div class="box-body box-profile">

              <h4>
               Modele :  ${vehicle.model}<br>
               Constructeur :  ${vehicle.constructor}<br>
                Nombre des places: ${vehicle.nbPlaces}
              </h4>


            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
        <div class="col-md-9">
          <div class="nav-tabs-custom">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#rents" data-toggle="tab">Reservations</a></li>

            </ul>
            <div class="tab-content">
              <div class="active tab-pane" id="rents">
                <div class="box-body no-padding">
                  <table class="table table-striped">
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>Client</th>
                      <th>Date de debut</th>
                      <th>Date de fin</th>
                    </tr>

                    <tr>
<c:forEach items="${rents}" var ="rent">
  <tr>
  <td>${rent.identifier}</td>

  <td>${rent.client.name}
  ${rent.client.lastName}
  </td>
  <td>${rent.debut}</td>
  <td>${rent.fin}</td>
  </tr>      </c:forEach>



                    </tr>

                  </table>
                </div>
              </div>
              <!-- /.tab-pane -->



              <!-- /.tab-pane -->
            </div>
            <!-- /.tab-content -->
          </div>
          <!-- /.nav-tabs-custom -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

    </section>
    <!-- /.content -->
  </div>

  <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
