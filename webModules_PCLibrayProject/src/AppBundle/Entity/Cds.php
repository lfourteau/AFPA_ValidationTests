<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Cds
 *
 * @ORM\Table(name="CDs", indexes={@ORM\Index(name="document_id", columns={"document_id"})})
 * @ORM\Entity(repositoryClass="AppBundle\Repository\CdsRepository")
 */
class Cds
{
    /**
     * @var integer
     *
     * @ORM\Column(name="duration", type="integer", nullable=false)
     */
    private $duration;

    /**
     * @var integer
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var \AppBundle\Entity\Documents
     *
     * @ORM\ManyToOne(targetEntity="AppBundle\Entity\Documents")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="document_id", referencedColumnName="id")
     * })
     */
    private $document;



    /**
     * Set duration
     *
     * @param integer $duration
     *
     * @return Cds
     */
    public function setDuration($duration)
    {
        $this->duration = $duration;

        return $this;
    }

    /**
     * Get duration
     *
     * @return integer
     */
    public function getDuration()
    {
        return $this->duration;
    }

    /**
     * Get id
     *
     * @return integer
     */
    public function getId()
    {
        return $this->id;
    }

   

    /**
     * Set document
     *
     * @param \AppBundle\Entity\Documents $document
     *
     * @return Cds
     */
    public function setDocument(\AppBundle\Entity\Documents $document = null)
    {
        $this->document = $document;

        return $this;
    }

    /**
     * Get document
     *
     * @return \AppBundle\Entity\Documents
     */
    public function getDocument()
    {
        return $this->document;
    }
}
