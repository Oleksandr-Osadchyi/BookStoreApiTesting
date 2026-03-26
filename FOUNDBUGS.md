Found bugs
---Create author api (POST /api/v1/Authors)
1) returns response status 200 but 'created' author is absent in get /api/v1/Authors response
2) returns response status 200 for request with already existing author id
3) returns response status 200 for request without mandatory fields id, idBook

---Get authors list by book id (GET /api/v1/Authors/authors/books/{idBook})
1) returns list of autors different from list of authors fetched from GET /api/v1/Authors response filtered by bookId

---Get author by id (GET /api/v1/Authors/{id})
1) returns author different from author fetched from GET /api/v1/Authors response filtered by Id

---Update author api (PUT /api/v1/Authors/{id})
1) returns response status 200
   but 'updated' author is absent in both get /api/v1/Authors response and GET /api/v1/Authors/{id} response
   while old data for 'updated' author is still present
2) returns response status 200 for update request with not existing author id
3) returns response status 200 for update request without mandatory fields id, idBook

---Delete author api (DELETE /api/v1/Authors/{id})
1) returns response status 200 but 'deleted' author is still present in get /api/v1/Authors response
2) returns response status 200 for delete request with not existing author id

---Create Book api (POST /api/v1/Books)
1) returns response status 200 but 'created' Book is absent in get /api/v1/Books response
2) returns response status 200 for request with already existing Book id
3) returns response status 200 for create request without mandatory

---Get Book by id (GET /api/v1/Books/{id})
1) returns Book different from Book fetched from GET /api/v1/Books response filtered by Id

---Update Book api (PUT /api/v1/Books/{id})
1) returns response status 200
   but 'updated' Book is absent in get /api/v1/Books response
   while old data for 'updated' Book is still present
2) returns response status 200 for update request with not existing Book id
3) returns response status 200 for update request without mandatory

---Delete Book api (DELETE /api/v1/Books/{id})
1) returns response status 200 but 'deleted' Book is still present in get /api/v1/Books response
2) returns response status 200 for delete request with not existing Book id