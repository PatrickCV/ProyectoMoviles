package pjrsolutions.ibuy.business.Compra;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import pjrsolutions.ibuy.business.base.FragmentoAbstracto;
import pjrsolutions.ibuy.datos.BaseDatosSQLite;
import pjrsolutions.ibuy.domain.ArticuloCompra;

public class Lector_QR extends FragmentoAbstracto implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mScannerView = new ZXingScannerView(getActivity());

        BD = new BaseDatosSQLite(getActivity(), null);

        return mScannerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result rawResult) {

        //Toast.makeText(getActivity(), "Contents = " + rawResult.getText() +", Format = " + rawResult.getBarcodeFormat().toString(), Toast.LENGTH_SHORT).show();

        String[] datos = rawResult.getText().split("-");
        ArticuloCompra articulo = new ArticuloCompra(datos[1],Float.parseFloat(datos[3]),1);
        BD.insertArticle(articulo, datos[2],datos[4],"0",datos[0]);

        getActivity().onBackPressed();
        // ((FragmentoActividadAbsPrincipal)getActivity()).agregueFragmentoAPila(new Nuevo_Articulo());
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        //Handler handler = new Handler();
        /*handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //mScannerView.resumeCameraPreview(Lector_QR.this);
                mScannerView.stopCamera();
                //onPause();
                //((FragmentoActividadAbsPrincipal)getActivity()).agregueFragmentoAPila(new Nuevo_Articulo());
            }
        }, 2000);*/

    }
    //((FragmentoActividadAbsPrincipal)getActivity()).agregueFragmentoAPila(new Nuevo_Articulo());
    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

}