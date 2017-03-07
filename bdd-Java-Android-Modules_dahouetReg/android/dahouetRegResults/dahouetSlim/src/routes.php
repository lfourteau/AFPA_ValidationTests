<?php

// Routes
$app->group('/api/v1', function () use ($app) {
// get all regates
    $app->get('/regates', function ($request, $response, $args) {
        $sth = $this->db->prepare("SELECT * FROM regate r INNER JOIN challenge c on r.cha_id = c.cha_id WHERE c.cha_date_debut < CURDATE() AND c.cha_date_fin > CURDATE()");
        $sth->execute();
        $regates = $sth->fetchAll();
        return $this->response->withJson($regates);
    });

    $app->get('/regate/[{id}]', function ($request, $response, $args) {
        $sth = $this->db->prepare("SELECT * FROM regate r inner join challenge c on c.cha_id = r.cha_id where r.reg_id = :id");
        $sth->bindParam("id", $args['id']);
        $sth->execute();
        $result = $sth->fetchAll();

        return $this->response->withJson($result);
    });
    $app->get('/result/regate/[{id}]', function ($request, $response, $args) {
        $sth = $this->db->prepare("select r.reg_libelle, v.voi_nom, v.voi_num_voile, per.per_prenom, per.per_nom, res.res_points
from regate r
inner join participation_voilier pv on r.reg_id  = pv.reg_id
inner join resultat res on res.par_voi_id = pv.par_voi_id
inner join voilier v on v.voi_id = pv.voi_id
inner join participant p on pv.voi_skipper_id = p.par_id
inner join personne per on per.per_id = p.per_id
where r.reg_id = :id
order by res.res_points");
        $sth->bindParam("id", $args['id']);
        $sth->execute();
        $result = $sth->fetchAll();

        return $this->response->withJson($result);
    });
});
