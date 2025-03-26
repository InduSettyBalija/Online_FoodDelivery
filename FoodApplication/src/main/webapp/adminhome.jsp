<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <style>
        body {
            font-family: 'Poppins', sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background: linear-gradient(45deg, #FFE580, #90EE90);
            color: #333;
            text-align: center;
        }
        .container {
            background-color: #ffffff;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 400px;
        }
        h1 {
            color: #50C878;
            font-size: 28px;
            margin-bottom: 20px;
        }
        a {
            display: block;
            margin: 15px auto;
            padding: 10px 20px;
            background-color: #50C878;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            width: 200px;
            font-weight: bold;
            transition: 0.3s ease-in-out;
        }
        a:hover {
            background-color: #3AA66B;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Admin Dashboard</h1>
        <a href="addRestarent.jsp">ADD RESTAURANT</a>
        <a href="addMenu.jsp">ADD MENU</a>
    </div>
</body>
</html>
