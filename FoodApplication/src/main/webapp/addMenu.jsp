<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Menu</title>
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
            width: 400px;
        }
        h1 {
            color: #50C878;
            font-size: 28px;
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin: 10px 0 5px 0;
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
            border: none;
            padding: 14px 20px;
            border-radius: 8px;
            font-size: 18px;
            width: 100%;
            cursor: pointer;
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
        <h1>Add Menu Item</h1>
        <form action="AddMenu">
            

            <label for="restid">Restaurant ID:</label>
            <input type="text" name="restid" id="restid" required>

            <label for="itemname">Item Name:</label>
            <input type="text" name="itemname" id="itemname" required>

            <label for="description">Description:</label>
            <input type="text" name="description" id="description" required>

            <label for="price">Price:</label>
            <input type="number" name="price" id="price" required>

            <label for="isavail">Is Available:</label>
            <input type="number" name="isavail" id="isavail" required>
            
            <label for="imagePath">ImagePath:</label>
            <input type="text" name="imagePath" id="imagePath" required>

            <input type="submit" value="Submit">
        </form>
    </div>
</body>
</html>
