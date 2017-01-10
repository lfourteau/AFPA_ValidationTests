<?php

namespace AppBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Comics
 *
 * @ORM\Table(name="comics", indexes={@ORM\Index(name="document_id", columns={"document_id"})})
 * @ORM\Entity(repositoryClass="AppBundle\Repository\ComicsRepository")
 */
class Comics
{
    /**
     * @var string
     *
     * @ORM\Column(name="kind", type="string", length=150, nullable=false)
     */
    private $kind;
    
    /**
     * @var text
     *
     * @ORM\Column(name="description", type="text", length=1500, nullable=false)
     */
    private $description;

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
     * Set kind
     *
     * @param string $kind
     *
     * @return Comics
     */
    public function setKind($kind)
    {
        $this->kind = $kind;

        return $this;
    }

    /**
     * Get kind
     *
     * @return string
     */
    public function getKind()
    {
        return $this->kind;
    }
    
    /**
     * Set description
     *
     * @param text $description
     *
     * @return Books
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $description;
    }

    /**
     * Get description
     *
     * @return text
     */
    public function getDescription()
    {
        return $this->description;
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
     * @return Comics
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
