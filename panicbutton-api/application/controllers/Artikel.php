<?php

defined('BASEPATH') OR exit('No direct script access allowed');

class Artikel extends CI_Controller {

    public function list(){
        if ($this->input->server('REQUEST_METHOD') == 'GET'){
            $list = $this->db->where('id_status_artikel','1')->get('artikel');
            if($list->num_rows() > 0){
                
                $hasil = [
                    'status'    => 'success',
                    'message'   => 'Berhasil get list artikel',
                    'data'      => $list->result()
                ];
                echo json_encode($hasil,JSON_PRETTY_PRINT);
            }else{
                
                $hasil = [
                    'status'    => 'failed',
                    'message'   => 'Data kosong',
                    'data'      =>null
                ];
                echo json_encode($hasil,JSON_PRETTY_PRINT);
            }
        }else{
            
            $hasil = [
                'status'    => 'failed',
                'message'   => 'request ditolak',
                'data'      =>null
            ];
            echo json_encode($hasil,JSON_PRETTY_PRINT);
        }
    }

    public function insert()
    {
        $judul                  = $this->input->post('judul',true);
        $isi                    = $this->input->post('isi', true);
        $foto                   = $this->input->post('foto', true);
        $tgl_dibuat             = date('Y-m-d H:i:s');
        $id_pengguna            = $this->input->post('id_pengguna', true);
        $id_status_artikel      = $this->input->post('id_status_artikel', true);

        $data = [
            'judul' => $judul,
            'isi' => $isi,
            'tgl_dibuat' => $tgl_dibuat,
            'id_pengguna' => $id_pengguna,
            'id_status_artikel' => $id_status_artikel,
            'judul' => $judul,
        ];

        if($foto != ''){
            $image_decoded  = base64_decode($foto);
            $encrypt        = substr(md5_mod('salt', date('Y-m-d H:i:s')), 0, 5);
            $image_name     = $encrypt.'.jpg';
            if(file_put_contents(base_url('assets/img').$image_name,$image_decoded)){
                $data['foto']   = $image_name;
            }
        }

        $insert = $this->db->insert('artikel',$data);
        if($insert){
            
            $hasil = [
                'status'    => 'success',
                'message'   => 'Berhasil simpan artikel',
            ];
            echo json_encode($hasil,JSON_PRETTY_PRINT);
            
        }else{
            
            $hasil = [
                'status' => 'failed',
                'message' => 'Gagal simpan artikel',
            ];
            echo json_encode($hasil,JSON_PRETTY_PRINT);
        }
    }

}
