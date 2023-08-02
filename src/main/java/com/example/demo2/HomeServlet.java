package com.example.demo2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "homeServlet", value = "/home-servlet")
public class HomeServlet extends HttpServlet {
    public List<Student> list = new ArrayList<>();

    @Override
    public void init() throws  ServletException{
        list.add(new Student(1,"Nguyen Van A",19));
        list.add(new Student(2,"Nguyen Van B",19));
        list.add(new Student(3,"Nguyen Van C",19));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        if (action == null){
            showListStudent(req, resp);
        }
        switch (action){
            case "DELETE":
                // Xoa theo id
                list.remove(findById(Integer.parseInt(req.getParameter("id"))));
                break;
            case "EDIT":
                // hien thi trang sua thong tin sinh vien
                showEditStudentForm(req,resp,Integer.parseInt(req.getParameter("id")));
                return; // Dừng xử lý để không hiển thị sinh viên khi đang chỉnh sửa
            default:
                break;

    }
        // nếu không có action hoặc ko phải là EDIT HOẶC DELETE thì tiếp tục hiển thị danh sách sinh viên
         showListStudent(req,resp);
    }

    public void showEditStudentForm(HttpServletRequest req, HttpServletResponse resp, int studentId) throws ServletException, IOException{
        // lấy danh sách cần chỉnh sửa từ danh sách(list)
        Student studentToEdit =findById(studentId);
        if (studentToEdit != null){
            // đặt thông tin sinh viên vào thuộc tính yêu cầu để truyền cho trang jsp để sửa đổi thông tin sinh viên
            req.setAttribute("studentToEdit",studentToEdit);
            req.getRequestDispatcher("/view/editStudent.jsp").forward(req,resp);
        }
        else {
            // Nếu không tìm thấy sinh viên cần chỉnh sửa, xử lý thông báo hoặc chuyển hướng tùy ý.
            resp.sendRedirect("/home-servlet");
        }
    }
    public void showListStudent(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("students", list);
        req.getRequestDispatcher("/view/listStudent.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("vao post");
        req.setCharacterEncoding("UTF-8");
        // phân tích action gửi lên là gì
        String action = req.getParameter("action");
        switch (action){
        case "ADD":
            // thêm mới
        String name = req.getParameter("name");
        String age = req.getParameter("age");
        int id = getNewId();
        list.add(new Student(id,name,Integer.parseInt(age)));
            showListStudent(req,resp);
        break;
            case "UPDATE":
                // cập nhật thông tin sinh viên
                int updateId = Integer.parseInt(req.getParameter("id"));
                Student studentToUpdate = findById(updateId);
                if (studentToUpdate != null) {
                    String updatedName = req.getParameter("name");
                    int updatedAge = Integer.parseInt(req.getParameter("age"));
                    studentToUpdate.setName(updatedName);
                    studentToUpdate.setAge(updatedAge);
                }
                break;
            default:
                break;
    }
        showListStudent(req, resp);
    }
    public int getNewId(){
        int maxId = 0;
        for (Student s:list
             ) {
            if (s.getId()>maxId){
                maxId=s.getId();
            }
        }
        return maxId+1;
    }

    public Student findById(int id){

        for (Student s:list
        ) {
            if (s.getId()==id){
                return s;
            }
        }
        return null;
    }



}
