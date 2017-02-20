<?php

// Routes

$app->group('/api/v1', function () use ($app) {

    // get all garages
    $app->get('/garage', function ($request, $response, $args) {
        $sth = $this->db->prepare("SELECT * FROM garage");
        $sth->execute();
        $garages = $sth->fetchAll();
        return $this->response->withJson($garages);
    });

    //get one garage
    $app->get('/garage/avis/[{id}]', function ($request, $response, $args) {
        $sth = $this->db->prepare("SELECT * FROM avis where garage_id = :id");
        $sth->bindParam("id", $args['id']);
        $sth->execute();
        $avis = $sth->fetchAll();
        $sth2 = $this->db->prepare("SELECT COUNT(DISTINCT note) as noteNumber, ROUND(AVG(note),1) as globalNote FROM note where garage_id = :id");
        $sth2->bindParam("id", $args['id']);
        $sth2->execute();
        $note = $sth2->fetchObject();
        $result = array();
        $result['avis'] = $avis;
        $result['noteNumber'] = $note->noteNumber;
        $result['note'] = $note->globalNote;

        return $this->response->withJson($result);
    });

    // Add a new reviews
    $app->post('/sendNote', function ($request, $response) {
        $avis = json_decode($request->getBody());
        $sql = "INSERT INTO note (garage_id, note) VALUES (:garage_id, :note)";
        $sth = $this->db->prepare($sql);
        $sth->bindParam("garage_id", $avis->garage_id);
        $sth->bindParam("note", $avis->note);
        $sth->execute();
        $avis->id = $this->db->lastInsertId();
        return $this->response->withJson($avis)->withStatus(201);
    });
    // Add a new reviews
    $app->post('/sendAvis', function ($request, $response) {
        $avis = json_decode($request->getBody());
        $sql = "INSERT INTO avis (garage_id, avis) VALUES (:garage_id, :avis)";
        $sth = $this->db->prepare($sql);
        $sth->bindParam("garage_id", $avis->garage_id);
        $sth->bindParam("avis", $avis->avis);
        $sth->execute();
        $avis->id = $this->db->lastInsertId();
        return $this->response->withJson($avis)->withStatus(201);
    });
    
});
