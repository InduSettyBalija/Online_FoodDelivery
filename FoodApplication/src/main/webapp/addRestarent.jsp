<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Restaurant</title>
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
        form {
            display: flex;
            flex-direction: column;
            align-items: flex-start;
        }
        label {
            margin-bottom: 5px;
            font-weight: bold;
            color: #333;
        }
        input[type="text"], input[type="number"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #50C878;
            border-radius: 5px;
        }
        input[type="submit"] {
            background-color: #50C878;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            align-self: center;
            font-weight: bold;
            transition: 0.3s ease-in-out;
        }
        input[type="submit"]:hover {
            background-color: #3AA66B;
            transform: scale(1.05);
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>ADD RESTAURANT</h1>
        <form action="AddRestaurant">
            

            <label for="name">Name:</label>
            <input type="text" name="name" id="name" required>

            <label for="cuisineType">Cuisine Type:</label>
            <input type="text" name="cusionType" id="cuisineType" required>

            <label for="deliveryTime">Delivery Time (min):</label>
            <input type="number" name="deleiverytime" id="deliveryTime" required>
            
            <label for="address">Address:</label>
            <input type="text" name="address" id="address" required>

            <label for="isActive">Is Active:</label>
            <input type="text" name="isactive" id="isActive" required>

            <label for="ratings">Ratings:</label>
            <input type="text" name="ratings" id="ratings" required>
            
            <label for="imagePath">ImagePath:</label>
            <input type="text" name="imagePath" id="imagePath" required>

            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
