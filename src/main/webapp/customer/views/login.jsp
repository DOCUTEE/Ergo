<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <!DOCTYPE html>
  <html lang="en">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  </head>

  <body>
    <div class="container mt-5">
      <div class="row justify-content-center">
        <div class="col-md-4">
          <h2 class="text-center">Customer Login</h2>
          <form action="${pageContext.request.contextPath}/customer/login" method="post">
            <div class="form-group">
              <label for="email">Email</label>
              <input type="email" class="form-control" id="email" name="email" required>
            </div>
            <div class="form-group">
              <label for="password">Password</label>
              <input type="password" class="form-control" id="password" name="password" required>
              <div>
                  <button type="submit" class="btn btn-primary btn-block mt-3">Sign in</button>
                  <a href="${pageContext.request.contextPath}/customer/register">Sign up</a>
                <a href="${pageContext.request.contextPath}/customer/forgot-password">Forgot Password?</a>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>

  </html>